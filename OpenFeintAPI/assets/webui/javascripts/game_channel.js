// Open the dashboard (or prompt login) when the leaf bar button is tapped.
OF.barButtonTouch = function() {
  OF.trackEvent('game_channel', 'dashboard_button');
  if (OF.user && OF.user.id) {
    OF.action('dashboard');
  } else {
    OF.action('showEnableView', { message: "Join the OpenFeint Community!", button: "Sign me up!" });
  }
};

(function($){
  $.fn.swipeIt = function() {
    var el = this;
    var s = {
      currentSlideIndex : 0,
      currentSlideOffset : 0,
      direction : null,
      dx : null,
      eventTypeStart : null,
      eventTypeMove : null,
      eventTypeEnd : null,
      i : null,
      lastSlideIndex : null,
      moveX : null,
      positionX : null,
      slideWidth : null,
      touchStartX : null,
      transition : null,
      startSwipeTime : null,
      endSwipeEndTime : null,
      swipeDuration : null,
      swipeSpeed : null,
      indicator : false,
    };
   
    s.eventTypeStart = (OF.isDevice) ? 'touchstart' : 'mousedown';
    s.eventTypeMove = (OF.isDevice) ? 'touchmove' : 'mousemove';
    s.eventTypeEnd  = (OF.isDevice) ? 'touchend' : 'mouseup';
  
    //STARTING EVENT
    $(el).live(s.eventTypeStart, function(){
      var sd = new Date();
      s.startSwipeTime = sd.getTime();
      swipeEvent(this);
    });
  
    // var buildIndicator = function() {
      if (s.indicator == true) {
        $(el).children().each(function(index) {        
          var elClass = (s.currentSlideIndex == index) ? elClass = 'current' : elClass = '';        
          $('.slide_indicator').append('<li class=' + elClass + '></li>');
        });
      }
    // };
    // 
    // buildIndicator();
  
    var indicateCurrentIndicator = function() {
      if (s.indicator == true) {
        $(".slide_indicator").children().removeClass('current');
        $(".slide_indicator li:eq(" + s.currentSlideIndex + ")").addClass('current');
      }
    };
    
    var swipeEvent = function(el) {
      s.currentSlideOffset = s.currentSlideIndex * -(s.slideWidth);
      s.slideWidth = $(el).children().outerWidth(true);
      s.lastSlideIndex = $(el).children().size() - 1;
      s.touchStartX = (OF.isDevice) ? event.touches[0].pageX : event.pageX; 
    
      var cancelTouch = function() {
        if (OF.isDevice) {
          $(el).unbind('touchmove');
          $(el).unbind('touchend');
        } else {
          $(el).unbind('mousemove');
          $(el).unbind('mouseup');
        }
      };
    
      //MOVING EVENT
      $(el).bind(s.eventTypeMove, function() { //mousemove or touchmove
        event.preventDefault();
        s.moveX = (OF.isDevice) ? event.touches[0].pageX : event.pageX; 
        s.dx = s.moveX - s.touchStartX;
        s.transition = '-webkit-transform 0s ease-in-out';
        s.positionX = (s.currentSlideOffset + s.dx);
        $(el).css({'-webkit-transition': s.transition ,'-webkit-transform' : 'translate3d(' + s.positionX + 'px, 0 ,0)'});
      });
      
      //ENDING EVENT
      $(el).bind(s.eventTypeEnd, function() {
        var ed = new Date();
        s.endSwipeTime = ed.getTime();
        s.swipeDuration = s.endSwipeTime - s.startSwipeTime;
        s.swipeSpeed = (s.swipeDuration * 0.002).toFixed(2); 
        s.swipeSpeed = (s.swipeSpeed > 0.25) ? 0.25 : (s.swipeDuration * 0.002).toFixed(2); 
      
        if (s.direction == null) {
          s.direction = s.dx;
          event.preventDefault();
        }
        if (Math.abs(s.dx) > 1) {
          s.direction = s.dx > 0 ? 'right' : 'left';
        }
        if (s.direction == 'left') {
          if (s.currentSlideIndex == s.lastSlideIndex) {
            s.transition = '-webkit-transform 0s ease-in-out';
            s.positionX = s.lastSlideIndex * s.slideWidth;
            s.dx = null;
          } else {
            s.i = 1; 
          }
        }
        if (s.direction == 'right') {
          if (s.currentSlideIndex == 0) {
            s.transition = '-webkit-transform 0s ease-in-out';
            s.positionX = 0;
            s.dx = null;
          } else {
            s.i = -1;
          }
        }
        if (Math.abs(s.dx) > Math.abs(s.slideWidth * .25)) {
          s.transition = '-webkit-transform ' + s.swipeSpeed + 's ' + 'ease-in-out';
          s.positionX = (s.currentSlideIndex + s.i) * s.slideWidth;
          s.currentSlideIndex = s.currentSlideIndex + s.i;
        } else {
          s.transition = '-webkit-transform ' + s.swipeSpeed + 's ' + 'ease-in-out';
          s.positionX = s.currentSlideIndex * s.slideWidth;
        }
        $(el).css({'-webkit-transition': s.transition ,'-webkit-transform' : 'translate3d(' + -(s.positionX) + 'px, 0 ,0)'});
        indicateCurrentIndicator();
        cancelTouch();
      });
    }
  };
})(jQuery);

(function($){
  $.fn.countDownOld = function(options) {
    var endHour = (options && options.endHour) || 24; //24:00 UTC is 5:00pm PST
    var el = this;
    
    var intervalID = setInterval(function() {
      
      var now = new Date(); // Get Current Time

      var end = new Date(now.getUTCFullYear(), now.getUTCMonth(), now.getUTCDate()); // get target hour today
      end.setUTCHours(endHour,00,0); //Make sure we are seting 24:00 UTC (5:00pm PST)
      
      if (now > end) end.setDate(end.getDate() + 1);  // ensure target end time is in the future
      
      var nowInMilliseconds = now.getTime(); // Convert Now to millseconds
      var endInMilliseconds = end.getTime(); // Convert end target time to millseconds
      var millsecondsTillEnd = (endInMilliseconds - nowInMilliseconds); // end minus now equals remaining time. Easy math in milli 
      var hoursTillEnd = Math.floor(millsecondsTillEnd / 3600000); // Multiplying buy millseconds in an hour and rounding the number down gives us the hours remaining 
      
       
      var minTillEnd = Math.floor(59 - now.getMinutes());
      var secondsTillEnd = (59 - now.getSeconds());
      secondsTillEnd = (secondsTillEnd < 10 ? '0' : '') + secondsTillEnd;
      
      $(el).html(hoursTillEnd + 'h ' +  minTillEnd + 'm ' +  secondsTillEnd + 's');

    }, 1000);

  }
})(jQuery);

OF.getTemperature = function(vote_target, vote_count, votes_bubble) {
    $('.thermometer .mercury').width(0);
    maxWidth = $('.thermometer').width() - 9;
    var x = vote_target / vote_count;
    var temperature = maxWidth / x;
    temperatureSpeed = 1500 / x;
    $('.thermometer .mercury').animate(
      {
        width: temperature
      }, 
      temperatureSpeed,
      function() {
        if (votes_bubble) {
          var exclamation = (vote_count == vote_target) ? "!" : '';
          $('.ammount span').text(addCommas(vote_count) + " votes" + exclamation);
          if ( vote_count > vote_target - (vote_target * .25)) { //greater than 75% full
            $('.ammount').css({
              'left' : (temperature - 54) + 'px', 
              'font-weight' : 'bolder'
            }).unhide();
            $('.ammount span').css('color', '#ea2929');
            $('.ammount .indicator').css({'left' : '61px'})
          } else if ( vote_count < vote_target * .25) { //less than 25% full
            $('.ammount').css({
              'left' : (temperature + 9) + 'px', 
              'font-weight' : 'bolder'
            }).unhide();
            $('.ammount .indicator').css({'left' : '-2px'})
          } else {
            $('.ammount').css({
              'left' : (temperature - 22) + 'px',
              'font-weight' : 'bolder'
            }).unhide();
          }
        } else {
          var exclamation = (vote_count == vote_target) ? "!" : '';
          $('.thermometer .mercury').text(addCommas(vote_count) + " votes" + exclamation);
        }
    });
    // if (votes_bubble) {
    //   var exclamation = (vote_count == vote_target) ? "!" : '';
    //   $('.ammount span').text(vote_count + " votes" + exclamation);
    //   if ( vote_count > vote_target - (vote_target * .25)) { //greater than 75% full
    //     $('.ammount').css({
    //       'left' : (temperature - 54) + 'px', 
    //       'font-weight' : 'bolder'
    //     }).unhide();
    //     $('.ammount span').css('color', '#ea2929');
    //     $('.ammount .indicator').css({'left' : '61px'})
    //   } else if ( vote_count < vote_target * .25) { //less than 25% full
    //     $('.ammount').css({
    //       'left' : (temperature + 9) + 'px', 
    //       'font-weight' : 'bolder'
    //     }).unhide();
    //     $('.ammount .indicator').css({'left' : '-2px'})
    //   } else {
    //     $('.ammount').css({
    //       'left' : (temperature - 22) + 'px', 
    //       'font-weight' : 'bolder'
    //     }).unhide();
    //   }
    // } else {
    //   //var exclamation = (vote_count == vote_target) ? "!" : '';
    //   //$('.thermometer .mercury').text(vote_count + " votes" + exclamation);
    //   // increment = 0;
    //   //      var animateVoteCount = function() {
    //   //        var i = (vote_count / temperatureSpeed) * 100;
    //   //      
    //   //        var incrementing = setInterval(function() {
    //   //          if (i < vote_count) {
    //   //            i = i + i;
    //   //          } else {
    //   //            i = vote_count;
    //   //            clearInterval(incrementing);
    //   //          }
    //   //          var exclamation = (vote_count == vote_target) ? "!" : '';
    //   //            $('.thermometer .mercury').text(Math.round(i) + " votes" + exclamation);
    //   //      
    //   //        }, 100);
    //   //      } 
    //   //animateVoteCount();
    // }
};

// OF.resetTemperature = function(vote_target, vote_count) {
//   $('.thermometer .mercury').width(0)
//   var x = vote_target / vote_count;
//   var maxWidth = $('.thermometer').width() - 9;
//   var temperature = maxWidth / x;
//   $('.thermometer .mercury').width(temperature);
//   $('.thermometer .mercury').css({
//     '-webkit-background-size' : (temperature + 5) + 'px 14px'
//   }); 
//   //$('.thermometer .mercury').text(vote_count);
// };

OF.countDown = {
  timerID: null,
  totalSecsTillEnd: 0,
  element: null,
  
  start: function(el, totalSecsTillEnd) {
    OF.countDown.element = $(el);
    if (totalSecsTillEnd) OF.countDown.totalSecsTillEnd = totalSecsTillEnd;
    OF.countDown.timerID = setInterval(OF.countDown.update, 1000);
  },
  
  stop: function() {
    clearInterval(OF.countDown.timerID);
  },
  
  update: function() {
    OF.countDown.totalSecsTillEnd--;
    OF.countDown.render();
  },
  
  render: function() {
    if (OF.countDown.totalSecsTillEnd > 0) {
      var totalSecsTillEnd = OF.countDown.totalSecsTillEnd;

      var now = new Date(); // Get Current Time
      var hoursTillEnd = Math.floor(totalSecsTillEnd / 3600); //Hours Remaining
      var hoursTillEndInSecs = (hoursTillEnd * 3600);
      var secsTillEndLeft = (totalSecsTillEnd - hoursTillEndInSecs);
      var minTillEnd = Math.floor(secsTillEndLeft / 60);
      var minTillEndLeftInSecs = (minTillEnd * 60);
      var secsTillEnd = Math.floor(secsTillEndLeft - minTillEndLeftInSecs);

      hoursTillEnd = (hoursTillEnd < 10 ? '0' : '') + hoursTillEnd;
      minTillEnd = (minTillEnd < 10 ? '0' : '') + minTillEnd;
      secsTillEnd = (secsTillEnd < 10 ? '0' : '') + secsTillEnd;
      if (totalSecsTillEnd <= -1) {
        $(el).html("Expired");
        OF.stop();
      } else {
        OF.countDown.element.html(hoursTillEnd + 'h ' +  minTillEnd + 'm ' +  secsTillEnd + 's');
      }
    }
  }
};

Date.prototype.abbrMonths = [
  'Jan',
  'Feb',
  'Mar',
  'Apr',
  'May',
  'Jun',
  'Jul',
  'Aug',
  'Sep',
  'Oct',
  'Nov',
  'Dec'
];

Date.prototype.DaysOfTheWeek = [
  'Sunday',
  'Monday',
  'Tuesday',
  'Wednesday',
  'Thursday',
  'Friday',
  'Saturday'
];

function addCommas(nStr) {
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}