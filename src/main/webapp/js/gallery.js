$(function(){
	/*$("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("active");
    });*/
	$("#stories a").click(function(e){
		window.open($(e.currentTarget).attr("href"), "news-wnd");
		return false;
	})
	
	$("#video-thumbnails img").click(function(e){
		var youtubeID = $(e.currentTarget).attr("youtubeid")
		embedSWF(youtubeID);
	})
})

function embedSWF(youtubeID){
    var params = { allowScriptAccess: "always" };
    var atts = { id: "myytplayer" };
    swfobject.embedSWF("http://www.youtube.com/v/"+youtubeID+"?enablejsapi=1&playerapiid=ytplayer&version=3",
                       "ytapiplayer", "640", "360", "8", null, null, params, atts);
}