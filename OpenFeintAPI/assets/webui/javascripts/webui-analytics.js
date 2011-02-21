// Google Analytics
OF.GA = {
  
  // True once the iframe is loaded
  loaded: false,
  
  // Holds a queue of analytics commands for before the iframe is loaded
  pending: [],
};

// Push a call through the iframe cross domain communicatinator
OF.GA.push = function() {
  // convert the arguments object into a normal array
  var args = Array.prototype.slice.call(arguments);

  var fragment = "#" + args.join(",");
  var url = "/webui/analytics.html" + fragment;    
  if (OF.serverUrl) { url = (OF.serverUrl + url).replace('//webui', '/webui'); }
  
  if ($('#google_analytics_frame').length > 0) {
    
    if (OF.GA.loaded && OF.GA.pending.length === 0) {
      $("#google_analytics_frame").attr("src", url);

      // the following code uses the resize hack to communicate to the iframe that it should re-inspect
      // its fragment.  It rotates from 1-100px in width;
      var width = parseInt($("#google_analytics_frame").attr("width"), 10) + 1;
      if (width > 100) { width = 1; }
      $("#google_analytics_frame").attr("width", width);
    } else {
      OF.GA.pending.push(args);
    }

  } else {        
    $('<iframe>')
      .load(OF.GA.iframeLoaded)
      .attr('id', 'google_analytics_frame')
      .attr('width', 99)
      .attr('height', 1)
      .attr('src', url)
      .appendTo('body');
  }
};

// When the analytics iframe loads up, start precessing the event queue.
OF.GA.iframeLoaded = function() {
  OF.GA.loaded = true;
  
  var pending = OF.GA.pending.shift();
  if (pending) {
    OF.GA.push.apply(null, pending);
    setTimeout(function() {
      OF.GA.iframeLoaded();
    }, 100);
  }
};

OF.GA.event = function(category, action, label, value) {
  OF.log('EVENT '+ category +', '+ action +', '+ label +', '+ value);
  OF.GA.push('_trackEvent', category, action, label, value);
};


// legacy aliases
OF.pushGACall = $.deprecate(OF.GA.push, 'OF.pushGACall()', 'OF.GA.push()');
OF.trackEvent = $.deprecate(OF.GA.event, 'OF.trackEvent()', 'OF.GA.event()');