/*==================================
* Author        : With Html
* Template Name : Eatery | Fast Food & Restaurant HTML template
* Version       : 1.0
==================================== */

/*=========== TABLE OF CONTENTS ===========
1. Today Special Slide
2. Staff Member Slide
3. Recent Update Slide
4. Food Gallery
5. Testimonial Slide
6. Date Picker
7. Form  Vailidation
8. Scroll to Top
===========*/


(function($) {
    'use strict';

    // ===== 1. Today Special Slide ==== 

    $(".today-special-slide").owlCarousel({

        loop: true,
        autoPlay: true,
        navigation: true,
        pagination: false,
        navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
        slideSpeed: 1200,
        items: 2,
        itemsDesktop: [1199, 2],
        itemsDesktopSmall: [980, 1],
        itemsTablet: [768, 1],
        itemsTabletSmall: false,
        itemsMobile: [380, 1]

    });


    // ===== 2. Staff Member Slide ==== 

    $(".staff-member-slide").owlCarousel({

        loop: true,
        autoPlay: false,
        navigation: true,
        pagination: false,
        navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
        slideSpeed: 1200,
        items: 3,
        itemsDesktop: [1199, 3],
        itemsDesktopSmall: [980, 1],
        itemsTablet: [768, 1],
        itemsTabletSmall: false,
        itemsMobile: [380, 1]

    });


    // ===== 3. Recent Update Slide ==== 

    $(".news-block-slide").owlCarousel({

        loop: true,
        autoPlay: false,
        navigation: true,
        pagination: false,
        navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
        slideSpeed: 1200,
        items: 3,
        itemsDesktop: [1199, 3],
        itemsDesktopSmall: [980, 1],
        itemsTablet: [768, 1],
        itemsTabletSmall: false,
        itemsMobile: [380, 1]

    });


    // ===== 4. Food Gallery ==== 

    $('.food-showcase').mixItUp();
	

    // ===== 5. Testimonial Slide ==== 

    $(".testimonial-slide").owlCarousel({

        loop: true,
        autoPlay: true,
        navigation: false,
        pagination: true,
        navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
        slideSpeed: 800,
        items: 1,
        itemsDesktop: [1199, 1],
        itemsDesktopSmall: [980, 1],
        itemsTablet: [768, 1],
        itemsTabletSmall: false,
        itemsMobile: [380, 1]

    });


    // ===== 6. Date Picker ==== 	

    $('#form-datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });


    // ===== 7. Form  Vailidation ==== 

    if ($('.form').length) {
        $('.form').validate({ // initialize the plugin
            rules: {
                name: {
                    required: true
                },

                address: {
                    required: true
                },

                email: {
                    required: true,
                    email: true
                },

                phone: {
                    required: true,
                    number: true
                },

                message: {
                    required: true
                },
                subject: {
                    required: true
                }
            },

        });
    }


    // ===== 8. Scroll to Top ==== 

    var html_body = $("html,body"),
        btn_top = $(".return-to-top");
    $(window).on("scroll", function() {
        if ($(window).scrollTop() > 200) {
            btn_top.fadeIn()
        } else {
            btn_top.fadeOut()
        }
    });
    btn_top.on("click", function() {
        html_body.animate({
            scrollTop: 0
        }, 1000)
    });
})(jQuery);