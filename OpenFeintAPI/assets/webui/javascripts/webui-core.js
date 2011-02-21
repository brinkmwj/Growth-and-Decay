/*global window: true, escape: true, unescape: true */
var OF, NativeInterface, $, jQuery, tmpl; // Implied Globals

// --- jQuery extensions ---
(function() {
  
  // Fade in an element that was hidden on page load with the .hidden class
  jQuery.fn.unhide = function(fadeSpeed) {
    $(this).removeClass('hidden').hide().fadeIn(fadeSpeed);
    return $(this);
  };
  
  // Shortcut for data('isLoading')
  // Used to disbale forms and buttons while an API request is working.
  jQuery.fn.isLoading = function(value) {
    return $(this).data('isLoading', value);    
  };
  
  // returns true if all input elements in this form have a value
  jQuery.fn.isFormFilled = function(failureMessage) {
    var valid = true; 
    $(this).find(':input').each(function(index,field) {
      if (field.value.length === 0) {
        OF.alert('Missing Data', failureMessage ? failureMessage : 'Please fill out all form fields.');
        valid = false;
        return false;
      }
    });
    return valid;
  };
  
  // Provides a method to lazy load a server resource as the user scroll the window
  // use nextPageLoaded callback to respond to new data
  // use nextPageShouldLoad to trigger your remote api calls to load the next page
  jQuery.lazyLoad = function(options) {
    var self = OF.page.eventHandle();
    var startPage = options.startPage || 1;
    var threshold = options.threshold || 0.5; //percentage of the window height left to scroll that will trigger the next page
    var nextPageLoaded      = options.nextPageLoaded;
    var nextPageShouldLoad  = options.nextPageShouldLoad;
    var isEmpty  = options.isEmpty;
    var isFinished  = options.isFinished;
    
    var pageHandler = function(page, data, forceFinish) {
      var finished = forceFinish || data.length === 0;
      
      if (finished) {
        if (page === 1 && isEmpty) { isEmpty(); }
        if (isFinished) { isFinished(); }
        $(self).unbind("scroll.lazyLoader");
      }
      
      if (data.length > 0) { nextPageLoaded(page, data); }
      
      $(self).data("lazyLoader-lastLoadedPage", page);
      $.defer(function() {
        $(self).data("lazyLoader-isLoading", false);
      });
    };
    
    $(self).data("lazyLoader-isLoading", true);
    // first load the initial page, then hookup the scroller
    nextPageShouldLoad(startPage, pageHandler.curry(startPage));
    

    $(self).bind("scroll.lazyLoader", function() {
      var scrollBottom = $(document).scrollTop() + $(window).height();
      var triggerLine = $(document).height() - ($(window).height() * threshold);
      // if we're not past the triggerline, bail
      if(scrollBottom < triggerLine) { return; }

      // if we're already loading the next page, bail
      if($(self).data("lazyLoader-isLoading")){ return; }

      var nextPage = ($(self).data("lazyLoader-lastLoadedPage") || startPage) + 1;

      $(self).data("lazyLoader-isLoading", true);
      nextPageShouldLoad(nextPage, pageHandler.curry(nextPage));
    });
  };
  
  
  // Execute this code in the next runloop
  jQuery.defer = function(js) {
    var args = Array.prototype.slice.call(arguments);
    args.shift();      

    setTimeout(function() {
      $.functionize(js, 'anonymous', 'defer').apply(null, args);
    }, 0);
  };
  
  jQuery.clone = function(obj) {
    return $.extend({}, obj);
  };
  
  // Time this javascript and print the time taken in ms to the console
  jQuery.profile = function(name, fn) {
    var start = new Date();
    var retVal = fn();
    var end   = new Date();
    OF.log('JS PROFILE:'+ name +' - '+ (end - start) +'ms');
    return retVal;
  };

  // Encode an object into a query string
  jQuery.urlEncode = function(options, prefix) {
    var result = '';

    if (options) {
      var values = [];
      for (var key in options) {
        if (options[key] !== null) {
          var paramName = prefix ? prefix +"["+ key +"]" : key;
          var value = options[key];

          if ($.isPlainObject(value)) {
            values.push($.urlEncode(value, paramName));
          } else {
            values.push(paramName +'='+ encodeURIComponent(value));
          }
        }
      }
      result = values.join('&');
    }

    return result;
  };

  // Opposite of $.urlEncode().  Convert query string to object
  jQuery.urlDecode = function(queryString) {
    if (!queryString) { return {}; }
    queryString = queryString.replace(/^\?/, ''); // ensure no leading ? character

    var result = {};
    var pairs = queryString.split('&');
    $.each(pairs, function() {
      var pair = this.split('=');
      result[pair[0]] = decodeURIComponent(pair[1]);
    });

    return result;
  };
  
  jQuery.htmlEncode = function(value) {
    return $('<div/>').text(value).html();
  };

  jQuery.htmlDecode = function(value) {
    return $('<div/>').html(value).text();
  };
  
  // Convert a string into a function object
  jQuery.functionize = function(js, file, name) {
    // It's a function already, return it as is
    if ($.isFunction(js)) { return js; }
    
    // It's a string, so make a function
    else if (typeof js === 'string') {
      if (js.match(/^\s*function/)) {
        js = ['(', js, ')'].join('');
      } else {
        js = ['(function() {', js, '})'].join('');
      }
      
      try {
        return eval(js);
      } catch(e) {
        throw(e.toString() +' in '+ $.functionize.errorLocation(file, name));
      }
      
    // Not a functionizable object
    } else {
      throw('ERROR: '+ $.functionize.errorLocation(file, name) +' Must be a string or function but was: '+ js);
    }
  };
  
  // Little helper function to create a name for for a function that failed
  // to be functionized.  Very useful for debugging.
  jQuery.functionize.errorLocation = function(file, name) {
    return [file.replace('.json', ''), ':', name].join('');
  };

  // foo/bar?thing=baz -> foo/bar.json?thing=baz
  jQuery.jsonifyUrl = function(url) {
    if (!url.match(/\.json/)) {
      if (url.match(/\?/)) {
        url = url.replace(/\?/, '.json?');
      } else {
        url += '.json';
      }
    }

    return url;
  };
  
  // Renders an array of objects 
  jQuery.renderCollection = function(partialName, items, innerKey) {
    if (items.length === 0) { return ''; }
    
    var theTmpl = tmpl(partialName);
    var html = [];
    
    if (!innerKey) {
      var keys = [];
      for (var k in items[0]) {
        if (items[0].hasOwnProperty(k)) { keys.push(k); }
      }
      if (keys.length > 1) {
        OF.log('ERROR: Ambiguous inner keys ['+ keys.join(', ') +']. Specify what key to use if there is more than one.');
        return;
      }
      if (keys.length === 0) {
        OF.log('ERROR: Cannot render empty object.');
      }
      
      innerKey = keys[0];
    }
    
    var index = 0;
    $.each(items, function() {
      var obj = this[innerKey];
      
      if (!obj.index)    { obj.index = index; }
      if (!obj.position) { obj.position = obj.index + 1; }
      index += 1;
      
      html.push(theTmpl(obj));
    });
    
    return $(html.join(''));
  };
  
  // Load a local script for a flow.  Asynchronously, blocking further execution
  jQuery.loadScript = function(path) {
    $.ajax({
      url: ['javascripts/', path, '.js?', new Date().getTime()].join(''),
      dataType: 'script',
      async: false
    });
  };
  
  jQuery.loadCss = function(path, async) {
    $.ajax({
      url: ['stylesheets/', path, '.css?', new Date().getTime()].join(''),
      async: typeof(async) === 'undefined' ? true : async,
      success: function(data) {
        $('<style></style>')
          .attr('type', 'text/css')
          .attr('id', path +'_css')
          .html(data)
          .appendTo('head');
      }
    });
  };
  
  // Pretty sure this is not needed right now...
  jQuery.ensureUpdate = function(selector, fn) {
    fn();
    
    var html = $(selector).html();
    if (html === null || $.trim(html).length === 0) {
      // OF.log('Ensuring Update... '+ selector);
      setTimeout(function() {
        jQuery.ensureUpdate(selector, fn);
      }, 100);
    }
  };
  
  // Returns a function to assign to a legacy variable which logs out a
  // deprecation when called.
  jQuery.deprecate = function(fn, oldName, newName) {
    return function() {
      OF.log("DEPRECATION WARNING: "+ oldName +" has been replaced by "+ newName);
      return fn.apply(null, arguments);
    };
  };
  
  /*  Code below is derivitave from Prototype, which is licensed
   *  Prototype JavaScript framework, version 1.6.1
   *  (c) 2005-2009 Sam Stephenson
   *
   *  Prototype is freely distributable under the terms of an MIT-style license.
   *  For details, see the Prototype web site: http://www.prototypejs.org/
   *
   *--------------------------------------------------------------------------*/

  Function.prototype.curry = function() {
    if (!arguments.length) { return this; }
    var theMethod = this, args = Array.prototype.slice.call(arguments, 0);
    return function() {
      var a = $.merge(args, arguments);
      return theMethod.apply(this, a);
    };
  };
   
  /* End prototype code */
  
}());



// Root OF object that all context is attached to.
var OF = {
  // True if web view is on an client device
  isDevice:    false,
  
  // True if in a web browser on a computer
  isBrowser:  false,
  
  // simple boolean to query what platform we are on
  deviceType: {
    iOS:      navigator.userAgent.match(/iPhone|iPad/),
    Android:  navigator.userAgent.match(/Android/)
  },
  
  // True if there is an object defined by "NativeInterface" that allows
  // javascript to directly interface with a native code object on the client
  hasNativeInterface: false,
  
  // The current page object
  page: null,
  
  // All pages in the flow are in this array.  The n-1 index is the currently
  // shown page, and zero index is the first page in the flow.
  pages: [],
  
  // Any data set here is preserved across all pages in the flow
  global: {},
  
  // "landscape" or "portrait".  This is set by the client via:
  //    OF.setOrientation(value)
  orientation: null
};

// Removes the current flow and sets a brand new root page with the one at "path"
OF.pages.replace = function(path) {
  OF.pages.splice(0, OF.pages.length);
  OF.push(path);
};

// Set device/browser flags
OF.isDevice = navigator.userAgent.match(/iPhone|iPad|Android/);
OF.isBrowser = !OF.isDevice;

// Legacy alias
OF.navigationStack = OF.pages;



// Log out a message.  Use instead of console.log because it shows up in the 
// client's debug log (i.e. Xcode)
OF.log = function(data) {
  if (OF.isBrowser) {
    console.log('WEBLOG:', data);
  }
  
  // iOS 3.x seems get clogged with too many actions being passed around.
  // So give the poor thing a break, will ya?  Geez.
  if (OF.device.ios3) { return; }
  
  // Send a log action to the client to show in the XCode console
  var message;
  if (OF.isDevice) {
    if (typeof data === 'object') {
      // break down the object.
      message = $.urlEncode(data);
    } else {
      // force into to a string
      message = ''+ data;
    }
    
    OF.action('log', { message: message });
  }
};

// Set orienation to "landscape" or "portrait".  This is called by the client
// whenever an orientation change ocurrs.
OF.setOrientation = function(newOrientation) {
  if (newOrientation) {
    OF.orientation = newOrientation;
    $('body')
      .removeClass('orientation_portrait')
      .removeClass('orientation_landscape')
      .addClass('orientation_'+ OF.orientation);
    
    if (OF.topPage() && OF.topPage().orientationChanged) {
      OF.topPage().orientationChanged(OF.orientation);
    }
  }
};

// These functions handle page load tasks
OF.init = {
  
  // true if we are not loading any content
  isLoaded: false,
  flowIsLoaded: false,
  
  // Fetch the first content
  firstPage: function() {
    var queryString = location.href.split('?')[1];
    var hasUrl = false;
    if (queryString) {
      var options = $.urlDecode(queryString);
      
      if (options.url) {
        hasUrl = true;
        $.ajax({
          url:'/webui/browser_config.json',
          dataType: 'json',
          complete: function(xhr) {
            var data = $.parseJSON(xhr.responseText);
            OF.init.clientBoot(data);
            OF.push(options.url);
          }
        });
      }
    }
    
    if (OF.isBrowser && !hasUrl) {
      OF.alert('ERROR', "No Content to Load! This page must be called with a url like /webui/?url=some/content_path");
    }
    
    // Create generic touch handlers for elements with data-* declared touch functions
    OF.touch.bindHandlers();
    
    // Don't allow touches while scrolling is happening
    var win = $(window);
    win.scroll(function() {
      OF.touch.isScrolling = true;
      clearTimeout(win.data('stopScrollingCallback'));
      
      if (OF.page.eventHandle) { OF.page.eventHandle().trigger("scroll"); }
      
      var timer = setTimeout(function() {
        OF.touch.isScrolling = false;
      }, 250);

      win.data('stopScrollingCallback', timer);
    });
  },
  
  clientBoot: function(options) {
    OF.hasNativeInterface = options.hasNativeInterface;
    
    OF.user     = options.user;
    if (!OF.user.name) { OF.user.name = null; }
    if (!OF.user.id || OF.user.id.toString() === '0') { OF.user.id = null; }
    
    OF.game       = options.game;
    OF.serverUrl  = options.serverUrl;
    
    OF.actions  = options.actions;
    OF.platform = options.platform;
    OF.device   = options.device;
    
    // Hack for slow iOS devices to behave themselves
    OF.device.ios3 = !!OF.device.os.match(/iPhone.*3\.\d\.\d/);
    if (OF.device.ios3) { OF.action.delay = 250; }
    
    OF.clientVersion = options.clientVersion;
    
    OF.dpi      = options.dpi;
    OF.setOrientation(options.orientation);
    
    OF.supports = options.supports || {};
    OF.supports.fixedPosition = (OF.platform === 'android' && OF.device.os.match(/v2\.2/) || OF.device.hardware === 'browser'); // TODO: this regex is very brittle.
    
    // Supplied by browser_config.json, for in browser testing of non embed poducts
    OF.manifestUrl = options.manifestUrl;
    
    OF.log("Client Booted - userID:"+ OF.user.id +" gameID:"+ OF.game.id +" platform:"+ OF.platform +" dpi:"+ OF.dpi);
    
    // Add classes to body element to allow various hardware and platform CSS customizations
    var body = $('body');
    body.addClass(OF.dpi).addClass(OF.platform);
    if (OF.supports.fixedPosition) { body.addClass('fixed_position'); }
    
    return true;
  },
  
  // Get the page ready for the client
  start: function() {
    OF.init.isLoaded = false;
    OF.init.scripts();
    OF.init.browser();
    OF.init.params();
    
    setTimeout(OF.init.pageViewTracking, 2000);
    
    // Load up the flow, if needed
    if (!OF.init.flowIsLoaded && OF.page.loadflow) {
      OF.page.loadflow();
      OF.init.flowIsLoaded = true;
    }
    
    if (!OF.page.init) { OF.page.init = $.noop; }
    if (OF.page.init.complete) {
      
      // Aleady ran init() so run resume() instead
      if (OF.page.resume) {
        $.defer(function() {
          try {
            OF.page.resume();
          } catch(e) {
            OF.alert('ERROR', "A script on this screen caused an error.\n appear: "+ e.toString());
          }
        });
      }
      
    } else {
      
      // Haven't yet run init()
      $.defer(function() {
        try {
          OF.page.init();
        } catch(e) {
          OF.alert('ERROR', "A script on this screen caused an error.\n appear: "+ e.toString());
        }
        OF.page.init.complete = true;
      });
    }
    
    $.defer(function() {
      if (OF.page.appear) {
        try {
          OF.page.appear();
        } catch(e) {
          OF.alert('ERROR', "A script on this screen caused an error.\n init: "+ e.toString());
        }
      }
    });
    
    OF.init.isLoaded = true;
    
    var buttonTitle = OF.page.barButton || OF.page.globalBarButton;
    var options = {};
    if (buttonTitle) { options.barButton = buttonTitle; }
    
    
    if (!OF.device.ios3 || (OF.device.ios3 && OF.api.activeRequestIDs.length === 0)) {
      OF.contentLoaded(options);
    }
  },
      
  // Compile string style javascript from JSON into functions
  scripts: function() {
    if (OF.page.init && typeof(OF.page.init) !== 'function') {
      OF.page.init = $.functionize(OF.page.init, OF.page.url, 'init');
      OF.page.init.complete = false;
    }
    
    if (OF.page.appear && typeof(OF.page.appear) !== 'function') {
      OF.page.appear = $.functionize(OF.page.appear, OF.page.url, 'appear');
    }
    
    if (OF.page.resume && typeof(OF.page.resume) !== 'function') {
      OF.page.resume = $.functionize(OF.page.resume, OF.page.url, 'resume');
    }
    
    if (!OF.init.flowIsLoaded && OF.page.loadflow && typeof(OF.page.loadflow) !== 'function') {
      OF.page.loadflow = $.functionize(OF.page.loadflow, OF.page.url, 'loadflow');
    }
  },
  
  // Show the browser toolbar, usable only when NOT on a device
  browser: function() {
    if (OF.isBrowser && $('#browser_toolbar').length === 0) {
      $.loadCss('browser_toolbar', false);
      $.get('browser_toolbar.html', function(data) {
        $(document.body).append(data);
      });
    }
  },
  
  // Creates nav bar button
  barButton: function() {
    var options = {};
    var buttonName = OF.page.barButton || OF.page.globalBarButton;
    
    if (OF.page.barButton)       { options.barButton      = buttonName; }
    if (OF.page.barButtonImage)  { options.barButtonImage = OF.page.barButtonImage; }
    OF.action('addBarButton', options);
  },
  
  // Google analytics page track for the just loaded page
  pageViewTracking: function() {
    OF.GA.push("_trackPageview", "/webui/" + OF.topPage().url);
  },
  
  // Populate page.query with query string object
  params: function() {
    var page = OF.topPage();
    if (!page.params) { page.params = {}; }
    if (page.url.match(/\?/)) {
      $.extend(page.params, $.urlDecode(page.url.split('?')[1]));
    }
  }
};

// Force-sets the title of the webui view
OF.forceSetTitle = function(title){
  if ($('#header .title').length > 0) {
    $('#header .title').html(title);
  }
};
  
// Shorthand for getting the top page object
OF.topPage = function() {
  return OF.pages[OF.pages.length-1];
};

// Legacy alias
OF.topNavigationItem = $.deprecate(OF.topPage, 'OF.topNavigationItem()', 'OF.topPage()');
  
// Loads the top page object on the navigation stack
OF.loadTopPage = function(completionCallback) {
  // Get the top navItem
  var navItem = OF.topPage();
  
  // Set the title
  document.title = navItem.title;
  
  // Set screen state
  OF.page = navItem;
  
  // Clear things out to start clean
  $('#page').html('&nbsp;');
  
  // Defer initialization to allow webview to redraw itself
  setTimeout(function() {
    // Continue initialization in another method that allows it to loop
    // until the client is actually ready with the content loaded in its DOM
    OF.loadTopPage.htmlReady(navItem, completionCallback);
  }, 50);
};

// Writes the pages html into the DOM.  Then is double checks that it worked,
// and if not then retry in a few milliseconds. Mobile browsers have been
// known to lock the DOM during page navigations, and this ensures the page
// update really works.
OF.loadTopPage.htmlReady = function(navItem, completionCallback) {
  // Wrap HTML if it's coming from a string
  if (typeof(navItem.html) === 'string') {
    navItem.html = ['<div id="event_context">', navItem.html, '</div>'].join('');
  }
  
  // Load HTML content
  $('#page')
    .html(navItem.html)
    .append("<div class='eventHandle' />")
    .attr('data-page_id', navItem.id);
  
  setTimeout(function() {
    // Ensure the client has loaded the HTML
    if ($.trim($('#page').html() || '').length === 0) {
      OF.log("Retrying... HTML not yet ready.");
      OF.loadTopPage.htmlReady(navItem);
      
    // HTML is loaded, continue initialization
    } else {
      // Save the event context
      OF.topPage().eventContext = $('#event_context')[0];
      
      // Perform initial page load javascript
      OF.init.start();
      if (completionCallback) { $.defer(completionCallback, navItem); }
      
      // Scroll to the previous position
      $.defer(function() {
        window.scroll(0, navItem.scrollPosition);
      });
    }      
  }, 100);
};
  
// Open a URL as a new page pushed onto the navigation stack
OF.push = function(url, options) {
  OF.init.isLoaded = false;
  if (!options) { options = {}; }
  
  var onComplete = options.complete;
  options.complete = null;
  
  // Ensure url is requesting .json
  url = $.jsonifyUrl(url);
  options.path = url;
  OF.log("Loading content: "+ url);
  
  // Save important page state before adding another page
  var params = options.params || {};
  options.params = null;
  
  if (OF.pages.length > 0) {
    OF.topPage().scrollPosition = window.scrollY;
  }
  
  OF.push.ready = function(pageJSON) {
    var loadThePage = function(data) {
      if (!OF.init.isLoaded) {
        if (OF.page) { OF.page.html = $('#page').contents().detach(); }

        var pageData = $.clone(data);
        $.extend(pageData, OF.pageFunctions);

        pageData.url = url;
        pageData.id = ['page', url.replace(/\W/g, '-'), (new Date().getTime())].join('_');
        pageData.scrollPosition = 0;
        pageData.modal = options.modal;
        OF.pages.push(pageData);
        OF.loadTopPage(onComplete);
        OF.topPage().params = $.extend(OF.topPage().params, params);
      }
    };
    
    $.defer(function() {
      if (pageJSON) {
        // Client provided the page content, just load it
        loadThePage(pageJSON);
      } else {
        // Request new page content via ajax
        $.ajax({
          url: url,
          dataType: 'json',
          success: loadThePage,
          error: function(xhr) {
            OF.init.isLoaded = true;
            OF.alert("Error", "Screen loading failed:\n"+ xhr.status +' '+ xhr.statusText);
            OF.loader.hide();
          }
        });
      }
    });
  };
  
  // Legacy alias
  OF.navigateToUrlCallback = OF.push.ready;
  
  OF.action('startLoading', options);
  OF.loader.show();
  if (OF.isBrowser) { OF.push.ready(); }
};

// Legacy alias
OF.navigateToUrl = $.deprecate(OF.push, 'OF.navigateToUrl()', 'OF.push()');

  
// reload the current page
OF.refresh = function() {
  if (OF.page) {
    OF.page.init.complete = false;
    $.defer(OF.loadTopPage);
  }
};

// Push a native controller onto the stack
OF.pushController = function(controllerName, options) {
  controllerName = controllerName +'?'+ $.urlEncode(options);
  if (OF.isDevice) {
    location.href = 'openfeint://controller/'+ controllerName;
  }
  OF.log('CONTROLLER:'+ controllerName);
};
  
// Client action that tells it a page is ready for viewing. When this fires
// all html, scripts, and images are loaded and ready
OF.contentLoaded = function(options) {
  if (!options) { options = {}; }

  options.title = document.title;
  if (OF.page.titleImage)      { options.titleImage      = OF.page.titleImage; }
  if (OF.page.barButton)       { options.barButton       = OF.page.barButton; }
  if (OF.page.barButtonImage)  { options.barButtonImage  = OF.page.barButtonImage; }
  OF.loader.hide();
  OF.action('contentLoaded', options);
};
  
// Set a bar button on iOS and a handler for it.  Title can be a string or
// a WebUI relative image path
OF.barButton = function(title, onTouch) {
  var options = {};
  if (title.match(/png$/)) {
    options.image = title.replace('xdpi.png', OF.dpi +'.png');
  } else {
    options.title = title;
  }
  
  $.defer(function() {
    OF.page.barButtonTouch = onTouch;
    OF.action('addBarButton', options);
  });
};

// Pop the top navigation item, and load the one behind it
OF.goBack = function(options) {
  OF.touch.cancel();
  
  if (!options) { options = {}; }
  if (OF.init.isLoaded && OF.pages.length > 1) {
    if (options.root) {
      OF.pages.splice(1, OF.pages.length - 1);
    } else {
      OF.pages.pop();
    }
    var onComplete = options.complete;
    delete options.complete;
    
    OF.action('back', options, function() {
      OF.loadTopPage(onComplete);
    });
  } else {
    if (!OF.device.ios3 || (OF.device.ios3 && OF.api.activeRequestIDs.length === 0)) {
      OF.contentLoaded(options);
    }
  }
  OF.init.isLoaded = true;
  OF.loader.hide();
};
  
OF.alert = function(title, message, options) {
  options = options || {};
  options.title = title;
  options.message = message;
  OF.action('alert', options);
  
  // browser alert if in the browser
  if (OF.isBrowser) { alert(options.title +"\n\n"+ options.message); }
};
  
OF.confirm = function(title, message, positive, negative, positiveCallback, negativeCallback) {
  OF.action('confirm', {
    title: title,
    message: message,
    positive: positive,
    negative: negative,
    callback: function(result) {
      if (result) {
        positiveCallback();
      } else {
        if (negativeCallback) { negativeCallback(); }
      }
    }
  });
  
  if (OF.isBrowser) {
    if (confirm(title +"\n\n"+ message)) {
      positiveCallback();
    } else {
      if (negativeCallback) { negativeCallback(); }
    }
  }
};

OF.loader = {
  count: 0,
  show: function() {
    if (OF.device.ios3) { return; }
    if (OF.loader.count === 0) {
      // OF.action('showLoader');
    }
    $('#header .loading').show();
    OF.loader.count += 1;
  },
  hide: function() {
    if (OF.device.ios3) { return; }
    OF.loader.count -= 1;
    if (OF.loader.count < 0) { OF.loader.count = 0; }
    if (OF.loader.count === 0) {
      $('#header .loading').hide();
      // OF.action('hideLoader');
    }
  }
};
  
// Store (or clear) the logged in user.
OF.userDidLogin = function(user) {
  if (user && user.id && user.id.length && user.id.toString() !== '0') {
    OF.user = user;
  } else {
    OF.user = { name: null, id: null};
  }
  
  if (OF.page && OF.page.userDidLogin) {
    OF.page.userDidLogin(user);
  }
};
  
OF.pageFunctions = {
  // hold an object filled with saved functions so the client can
  // use them as callbacks.
  savedFunctions: {},
  
  eventHandle: function() {
    return $("#page > .eventHandle");
  },
  saveFunction: function(fn, someId) {
    if ($.isFunction(fn)) {
      var string = [someId, new Date().valueOf(), Math.floor(Math.random()*1000000)].join('-');
      OF.page.savedFunctions[string] = fn;
      return 'OF.page.savedFunctions["'+ string +'"]';
    }
  }
};
  
OF.settings = {
  write: function(key, value) {
    var type = typeof(value);
    if (type === 'number') {
      type = (Math.floor(value) === value) ? 'int' : 'float';
    }
    
    OF.action('writeSetting', { key: key, value: value, type: type });
  },
  
  read: function(key, type, handler) {
    OF.action('readSetting', { key: key, type: type, callback: handler });
  }
};

if (typeof NativeInterface === "object" && NativeInterface.frameworkLoaded) {
  NativeInterface.frameworkLoaded();
}

// --- GO ---
$(document).ready(OF.init.firstPage);
