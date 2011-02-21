(function() {
  
  // Array of core javascript files required for WebUI to function
  var scripts = [
    // Eeverything is built on jQuery
    'jquery',
    
    // Javascript powered template library
    'template',
    
    // WebUI base functions, required for everything
    'webui-core',
    
    // Comunicates with our server API
    'webui-api',
    
    // Handle communication with the client via action passing
    'webui-action',
    
    // Multi-touch handling to make elements tappable
    'webui-touch',
    
    // Google Analytics handling
    'webui-analytics'
  ];
  
  // If our browser environment oes not support native JSON support, then
  // include json2.js to handle that for us.
  if (typeof(JSON) === 'undefined') scripts.push('json2');
  
  // Use the current time to ensure the assets are pulled fresh from the
  // disk and not some cache blocking us form the up to date versions. On
  // the client these are stored locally on disk anyway, so there is little
  // performance gain from a cache that might serve up a stale file.
  var cacheBuster = new Date().getTime();
  
  // Write a script tag for each core script to the document
  for (var i = 0; i < scripts.length; i++) {
    document.write('<script type="text/javascript" src="javascripts/'+ scripts[i] +'.js?'+ cacheBuster +'"></scr'+'ipt>');
  }
  
  // After loading those scripts, go ahead and load up the main css file.
  // Use the JS loader so the relative urls come fom the root of webui, and not
  // the stylesheet's location.
  document.write('<script type="text/javascript"> $.loadCss("webui"); </scr'+'ipt>');
  
}());