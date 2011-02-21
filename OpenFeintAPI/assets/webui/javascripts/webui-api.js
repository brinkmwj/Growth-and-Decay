/*jslint browser: true, devel: true, undef: true, nomen: true, eqeqeq: true, plusplus: true, bitwise: true, regexp: true, newcap: true, immed: true */
var OF, $; // Implied Globals

//
// OF.api
//   - Public API
//

// Make an api request.  GET is the default.
OF.api = function(path, options) {
  options    = options        || {};
  var params = options.params || {};
  
  var request = new OF.api.RequestObject($.jsonifyUrl(path), params, options);
  OF.api.activeRequests[request.id] = request;
  OF.api.activeRequestIDs.push(request.id);
  
  // In most environments, just go and start the request
  if (!OF.device.ios3) {
    request.start();
  }
  
  // iOS3 needs to run API reqs sequentially, not in parallel.  So only start the
  // the request if no other request is going on.
  if (OF.device.ios3 && OF.api.activeRequestIDs.length === 1) {
    request.start();
  }
  
  return request;
};

// Allow short hand api calls for all 4 http methods supported.
//   OF.api()         GET request by default
//   OF.api.get()
//   OF.api.post()
//   OF.api.put()
//   OF.api.delete()
//
$.each(['get', 'post', 'put', 'delete'], function(i, httpMethod) {
  OF.api[httpMethod] = function(path, options) {
    options = $.extend(options, { method: httpMethod.toUpperCase() });
    return OF.api(path, options);
  };
});






//
// OF.api
//   - Private Implementation
//

// Holds requests that are open and awaiting a callback
OF.api.activeRequests = {};
OF.api.activeRequestIDs = [];
  
// Make a new apiRequestObject with
//   new apiRequestObject(path, params, options);
OF.api.RequestObject = function(path, params, options) {
  this.id = [
    path.replace(/\W/g, '-'),
    new Date().valueOf(),
    Math.floor(Math.random()*1000000)
  ].join("_");
  
  this.page = OF.page;
  this.background = options.background;
  this.path = path;
  this.params = params;
  this.httpParams = options.httpParams;
  this.loader = $(options.loader);
  this.method = options.method || 'GET';
  
  // State tracking
  this.isComplete  = false;
  this.isCancelled = false;
  this.isStarted   = false;
  
  // Custom Callbacks
  this.successCallback  = options.success;
  this.failureCallback  = options.failure;
  this.completeCallback = options.complete;
  
  //status based callbacks
  var statusCallbacks  = {};
  
  $.each([200, 201, 400, 401, 404, 406, 409, 500, 503], function() {
    statusCallbacks["on" + this] = options["on" + this];        
  });
  
  this.statusCallbacks = statusCallbacks;
  
  // Fake session in browser, only works in development server mode
  if (OF.isBrowser) {
    params.session_device_id = OF.device.identifier;
    params.session_user_id   = OF.user.id;
    params.session_game_id   = OF.game.id;
  }
  
  // Begins the request
  this.start = function() {
    OF.log('API Request Started: '+ this.method +' '+ path +'?'+ $.urlEncode(params));
    
    this.isStarted = true;
    
    if (!this.background) { OF.loader.show(); }
    if (this.loader) {
      if (this.loader.hasClass('button')) {
        this.loader
          .data('buttonHtml', this.loader.html())
          .html('Loading&hellip;');
      } else {
        this.loader.fadeIn();
      }
    }
    
    var sendAction = function(ctx) {
      OF.action('apiRequest', {
        path:       ctx.path,
        method:     ctx.method,
        params:     $.urlEncode(ctx.params),
        httpParams: $.urlEncode(ctx.httpParams),
        request_id: ctx.id
      });
    };
    
    var sendActionRetry = function(ctx) {
      if (OF.init.isLoaded) {
        setTimeout(function() {
          sendAction(ctx);
        }, 500);
      } else {
        setTimeout(function() {
          sendActionRetry(ctx);
        }, 250);
      }
    }
    
    sendActionRetry(this);
    
    // Ajax for browser
    if (OF.isBrowser) {
      var thisReq = this;
      $.ajax({
        url:      this.path,
        data:     this.params,
        type:     this.method,
        dataType: 'json',
        
        complete: function(xhr, textStatus) {
          OF.api.completeRequest(thisReq.id, xhr.status.toString(), xhr.responseText);
        }
      });
    }
  };
  
  // Completion handler
  this.complete = function(status, response) {
    // sanity check response data
    var data;
    if ($.isPlainObject(response)) {
      // Data is already a javascript object, simply use it.
      data = response;
    } else {
      // Data is a string, attempt to parse as JSON
      if ($.trim(response).length) {
        try {
          data = $.parseJSON(response);
        } catch(e) {
          // Failed to parse as JSON, assign an empty object to data and let
          // the response be logged out to the console.  Hopefully this was
          // served with an error response code, so the failure handler of the
          // api request should be triggered.
          data = {};
        }
      }
      
      // If after all that we still have no data, jsut use an empty object.
      if (!data) { data = {}; }
    }
    
    this.isComplete = true;
    if (!this.background) { OF.loader.hide(); }
    if (this.isCancelled || this.page !== OF.page) { return; }
    
    if (this.loader) {
      if (this.loader.hasClass('button')) {
        this.loader.html(this.loader.data('buttonHtml'));
      } else {
        this.loader.hide();
      }
    }
    
    // status code based callbacks
    var callback = this.statusCallbacks["on" + status];
    if (callback) { callback(data, status); }
    
    // Success
    if (status.match(/^2/)) {
      OF.log('API Request Complete: '+ this.path);
      if (OF.isBrowser) { console.log('    ', data); }

      if (this.successCallback) {
        this.successCallback(data, status);
      }

    // Failure
    } else {
      OF.log('API Request Failed: '+ this.path +'    '+ response);
      if (OF.isBrowser) { console.log('    ', data); }

      if (this.failureCallback) {
        var handledFailure = true;
        handledFailure = this.failureCallback(data, status);
        if (handledFailure === false) {
          OF.api.handleError(data, status);
        }
      } else {
        OF.api.handleError(data, status);
      }
    }
            
    // Complete
    if (this.completeCallback) {
      this.completeCallback(data, status);
    }
  };
  
  this.cancel = function() {
    if (!this.isComplete) {
      this.isCancelled = true;
      if (this.completeCallback) {
        this.completeCallback({ exception: { "class": 'CanceledRequest', message: 'This request was canceled' } }, '408');
      }
    }
  };
};

// Default error handler for API request failures.  Just pop an alert.
OF.api.handleError = function(data, status) {
  if (data.exception) {
    if (status === '0') {
      OF.alert('Unable to Connect', 'Please check that you have cellular or WiFi service and try again.');
    } else {
      alert(data.exception.message);
    }
  } else {
    OF.alert('ERROR: '+ status, 'Oops! There was an error communicating with the server.');
  }
};

// Execute the requests response handling and then destroy it.
OF.api.completeRequest = function(requestID, status, response) {
  var req = OF.api.activeRequests[requestID];
  if (req) {
    req.complete(status, response);
    delete OF.api.activeRequests[requestID];
    OF.api.activeRequestIDs.splice(OF.api.activeRequestIDs.indexOf(requestID), 1);
  } else {
    OF.log("WARNING: Request ID not found. Maybe it already completed. ID: "+ requestID);
  }
  
  if (OF.device.ios3 && OF.api.activeRequestIDs.length > 0) {
    $.defer(function() {
      OF.api.activeRequests[OF.api.activeRequestIDs[0]].start();
    });
  }
  
  if (OF.device.ios3 && OF.api.activeRequestIDs.length === 0) {
    OF.contentLoaded();
  }
};




// Legacy alias
OF.api.request = $.deprecate(OF.api, 'OF.api.request()', 'OF.api()');
