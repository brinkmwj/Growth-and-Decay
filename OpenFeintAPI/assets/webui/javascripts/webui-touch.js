/*jslint browser: true, devel: true, undef: true, nomen: true, eqeqeq: true, plusplus: true, bitwise: true, regexp: true, newcap: true, immed: true */
var OF, $, jQuery; // Implied Globals


//
// OF.touch
//   - Public API
//

// Use to make elements tappable and execute the provided function.
// Works similarly to click() and friends.
jQuery.fn.touch = function(func) {
  if (func) {
    OF.touch.bindHandlers(this, func);
  } else {
    OF.touch(this);
  }
  return $(this);
};

// Toggle the touch state of an element super quick to trigger preloading
// of images refrenced in the .touched css.
jQuery.fn.pretouch = function() {
  var elems = $(this);
  elems.addClass('touched');
  $.defer(function() {
    elems.removeClass('touched');
  });
  return elems;
};




//
// OF.touch
//   - Private Implemenation
//

// Perform the touch function bound to this element.
OF.touch = function(element) {
  
  // Leaving this out for now, it was preventing touces from being handled
  // if (!OF.init.isLoaded) { return; }
  
  element = $(element);
  
  // trigger GA event tracking
  if (element.data().event) {
    var args = element.data().event.split(',');
    OF.trackEvent.apply(OF, args);
  }
  
  // trigger any custom touch() function
  element.trigger('touch');
  
  // navigate to path in data-href 
  if (element.data().href) {
    var url = element.data().href;
    if (!url.match(/\.json/)) {
      if (url.match(/\?/)) {
        url = url.replace(/\?/, '.json?');
      } else {
        url = url + '.json';
      }
    }
    
    var options = {};
    if (element.attr('title')) { options.title = element.attr('title'); }
    
    OF.push(url, options);
  
  // call native action in data-action
  } else if (element.data().action) {
    OF.action(element.data().action);
  
  // TODO: This might be deprecated at this point, especially due to xp
  // Push a native controller of name in dta-controller
  } else if (element.data().controller) {
    OF.pushController(element.data().controller);
  }
};



// --- Variables to track data about the last touch ---

// Global pointer to the element that was last tapped.
// Cleared on touchend or touchmove.
OF.touch.element = null;

// Location of the down touch.  Used to test how far we have moved since
// touch down.
OF.touch.x = null;
OF.touch.y = null;
  
// true if the page is scrolling about, dont allow touches until it settles
OF.touch.isScrolling = false;

// Make elements touchable.  If no arguments are passed in, it will setup the
// handlers for data-href, data-event and data-action
OF.touch.bindHandlers = function(elems, onTouch) {
  if (elems) {
    var ctx = OF.topPage().eventContext;
    var sel = typeof(elems) === 'string' ? elems : elems.selector;
    elems = $(sel, ctx);
  } else {
    elems = $('*[data-href], *[data-event], *[data-action], *[data-controller]');
  }
  
  if (onTouch) {
    elems.die('touch');
    elems.live('touch', onTouch);
  }
  
  // On a device, so use moultitouch events
  if (OF.isDevice) {
    elems
      .die('touchstart')
      .die('touchend');

    elems
      .live('touchstart', OF.touch.touchstart)
      .live('touchend',   OF.touch.touchend);
      
  // In a browser, so use clicks and mouse events
  } else {
    elems
      .die('mousedown')
      .die('mouseup')
      .die('click');
    
    elems
      .live('mousedown',  function() { $(this).addClass('touched'); })
      .live('mouseup',    function() { $(this).removeClass('touched'); })
      .live('click',      function() { OF.touch(this); });
  }
};

// Set up the element to be tapped
// * set the element in the global tapped element slot for later comparison
// * give it the 'touched' class for visual feedback
OF.touch.touchstart = function(event) {
  // An element previously "touched" is a child of this element.  This
  // means that the event is propogating up and we only want to process
  // the farthest down child.  So just ignore processing the touchstart
  if (OF.touch.element && $(OF.touch.element).parents($(this)).length > 0) {
    return;
  }
  
  // Cancel all touches if their is another element in a different part
  // of the page that was touch, or if we are scrolling.
  else if (OF.touch.element || OF.touch.isScrolling) {
    return OF.touch.cancel(this);
  }
  
  event = event.originalEvent;
  
  OF.touch.element = this;
  OF.touch.x = event.targetTouches[0].screenX;
  OF.touch.y = event.targetTouches[0].screenY;
  
  // delay the touch highlight if it's a scrollable view
  if (OF.page.scrolling) {
    var savedThis = $(this);
    this.onTouchTimer = setTimeout(function() {
      savedThis.addClass('touched');
    }, 50);
  } else {
    $(this).addClass('touched');
  }
};


// If we didn't scroll, and the element ending the touch is the same one that
// started it, then perform the function bound to touched element
OF.touch.touchend = function(event) {
  event = event.originalEvent;
  
  if (OF.touch.element === this) {
    // Perform the action the element represents
    OF.touch(this);
    
    // Take off the touched status after a delay to allow the new
    // view to animate in.
    var savedThis = $(this);
    setTimeout(function() {
      savedThis.removeClass('touched');
    }, 300);
  }
  
  OF.touch.cancel();
};


// Cancel the current touch.  A canceled touch will do NOTHING when it ends.
OF.touch.cancel = function() {
  if (!OF.touch.element) { return; }
  
  var el = OF.touch.element;
  clearTimeout(el.onTouchTimer);
  $(el).removeClass('touched');
  
  OF.touch.element = null;
  OF.touch.x = null;
  OF.touch.y = null;
};

// Set an ontouchmove handlers on the root document.  This does 2 things:
//
// 1. If a touch moves more than a threshold of pixels, cancel the touch so
//    that ending the touch does not trigger any function.
//
// 2. If a page has disabled is scrolling, then prevent the ontouchmove event
//    from doing it's default thing.  This locks the scrolling of the page in
//    place
//
$(document).ready(function() {
  document.ontouchmove = function(event) {
    // Cancel touches if we drag too far
    var limit = OF.page.scrolling ? 5 : 25;
    var x = Math.abs(event.targetTouches[0].screenX - OF.touch.x);
    var y = Math.abs(event.targetTouches[0].screenY - OF.touch.y);

    if (x > limit || y > limit) {
      OF.touch.cancel();
    }

    // Prevent scrolling if we are not scrollable
    if (!OF.page.scrolling) {
      event.preventDefault();
    }        
  };
});