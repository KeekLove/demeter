/*-------------------------------------------------------------------------
 * RENDIFY - Custom jQuery Scripts
* ------------------------------------------------------------------------*/
$(document).ready(function () {
    $(".cssmenu").prepend('<div id="indicatorContainer"><div id="pIndicator"><div id="cIndicator"></div></div></div>');
    var b = $(".cssmenu>ul>li:first");
    $(".cssmenu>ul>li").each(function () {
        $(this).hasClass("active") && (b = $(this))
    });
    var c = b.position().left,
    d = b.width();
    c = c + d / 2 - 6,
    b.hasClass("has-sub") && (c -= 6),
    $(".cssmenu #pIndicator").css("left", c);
    var e, f;
    $(".cssmenu pIndicator"),
    $(".cssmenu>ul>li").hover(function () {
        e = $(this);
        var b = e.width();
        f = $(this).hasClass("has-sub") ? e.position().left + b / 2 - 12 : e.position().left + b / 2 - 6,
        $(".cssmenu #pIndicator").css("left", f)
    },
    function () {
        $(".cssmenu #pIndicator").css("left", c)
    }),
    $(".cssmenu>ul").prepend('<li id="menu-button"><a>MENU</a></li>'),
    $("#menu-button").click(function () {
        $(this).parent().hasClass("open") ? $(this).parent().removeClass("open") : $(this).parent().addClass("open")
    })
})