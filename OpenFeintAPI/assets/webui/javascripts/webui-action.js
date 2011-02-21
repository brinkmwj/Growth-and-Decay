/*jslint browser: true, devel: true, undef: true, nomen: true, eqeqeq: true, plusplus: true, bitwise: true, regexp: true, newcap: true, immed: true */
/*global escape: true, unescape: true */
var OF, $, NativeInterface; // Implied Globals

//
// OF.action
//   - Public API
//


// Send an action to the client that is to be handled in native code.  The
// options is serialized and sent with the action.
OF.action = function(actionName, options, afterAction) {
  for (var key in options) {
    if ($.isFunction(options[key])) {
      options[key] = OF.page.saveFunction(options[key], actionName +'-'+ key);
    }
  }
  
  if (OF.supports.actionJSON) {
    // Encode options as an awesome JSON packet
    actionName += '?'+ encodeURIComponent(JSON.stringify(options || {}));
  } else {
    // Encode params k=v old school form style for old clients
    var query = $.urlEncode(options);
    if (query.length > 0) {
      actionName += '?'+ query;
    }
  }
  
  OF.action.queue.push({
    uri:         actionName,
    afterAction: afterAction
  });
  
  var isStalled = OF.action.queue.length > 1 && new Date().getTime() - OF.action.lastActionSentAt > 5000;
  if (OF.action.queue.length === 1 || isStalled) {
    $.defer(OF.action.send);
  }
};

// Check if the client supports a native action.  This checks the list of
// actions passed in on OF.clientBoot()
OF.action.isSupported = function(actionName) {
  return OF.actions.indexOf(actionName) !== -1;
};




//
// OF.action
//   - Private Implementation
//

// delay between actions, in ms
OF.action.delay = 75;
OF.action.lastActionSentAt  = 0;

// Legacy alias
OF.sendAction = OF.action;

OF.action.queue = [];
OF.action.send = function() {
  if (OF.action.queue.length === 0) { return; }
  var actionObj = OF.action.queue.shift();
  var actionPath = actionObj.uri;
  var uri = 'openfeint://action/' + actionPath;
  
  if (OF.isDevice) {
    if (OF.hasNativeInterface) {
      NativeInterface.action(uri);
    } else {
      // Change the url on an iframe to pass actions to the client where
      // we have no NativeInterface
      $.defer(function() {
        $('#action_frame').attr('src', uri);
      });
      
      // --- HACK ALERT ---
      //
      // iOS (all versions) tends to lose this action message, no idea why :(
      // It does seem to safe to resend it 1/4 second later, and this seems to
      // ensure it really happens
      if (OF.platform == 'ios') {
        if (uri.match(/contentLoaded/)) {
          setTimeout(function() {
            $('#action_frame').attr('src', uri);
          }, 250);
        }
      }
      
      // Does an iframe location change work on all platforms without a native interface?
      // If not, we have to do it this way:
      //
      //   location.href = uri;
    }
  }
  
  //// Debugging stuff to verify action passing mechanism
  // 
  // if ($('body > ul').length === 0) { $('body').append('<ul>') }
  // $('body > ul').append('<li>uri: '+ uri +'<br>src: '+ $('#action_frame').attr('src') +'</li>');
  
  if (actionObj.afterAction) {
    $.defer(actionObj.afterAction);
  }
  
  if (!actionPath.match(/^log\W/)) {
    if (OF.isBrowser) {
      if (OF.supports.actionJSON) {
        var name = actionPath.split('?')[0];
        var data = actionPath.split('?')[1];
        console.log('ACTION: '+ name +' '+ decodeURIComponent(data));
      } else {
        console.log('ACTION: '+ actionPath);
      }
    }
  }
  
  setTimeout(OF.action.send, OF.action.delay);
  OF.action.lastActionSentAt  = new Date().getTime();
};

OF.actionIsSupported = $.deprecate(OF.action.isSupported, 'OF.actionIsSupported()', 'OF.action.isSupported()');