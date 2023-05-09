$(document).ready(()=>{
   //gnb
  $("#gnb > ul > li").bind('mouseenter focusin',function(){
    console.log('::::::::::::mouse event!');
    $(this).parent().find(".subMenu").addClass("active");
    $("#header .menubar").addClass("active");

    const height = $(this).find('ul.active').height()
    $("#header .menubar").css({ height: height + 140 });
    if($('#gnb > ul > ul').css({height : 0})){
      $("#header .menubar").css({ height: 200 });  
    };
    if($('.admin').hasClass('active')){
      $("#header .menubar").css({ height: 618 })
    };
  });

  $(".menubar").mouseleave(() => {
    $("#gnb > ul > li").find('.subMenu').removeClass('active');
    $(this).find('.menubar').removeClass('active')
    $("#header .menubar").css({ height: 96 })
  });

  //lnb
  $(".lnbList > ul > li").click(function(e){
		e.preventDefault();
		if($(this).hasClass("active") == false){
			$(".lnbList > ul > li").removeClass("active");
			$(this).addClass("active");
			$(".lnbList ul ul").slideUp(300);
			$(this).children("ul").slideDown(300);
		}
		else{
			$(this).removeClass("active");
			$(this).children("ul").slideUp(300);
		}
	});
  //checkbox
  $("table td label").click(function() {
    let chk_arr =[];
    $('input[name=checkBtn]:checked').each(()=>{
      let chk = $(this).val();
      chk_arr.push(chk);
      console.log(this,':::::::');
    })
		if($(this).siblings("input").is(':checked')) {
			$(this).siblings("input").is(':checked')
		} else {
			$(this).siblings("input").prop("checked", false)
		}
	});
})
// 요청사항 : 로그인 여부에 따른 관리자메뉴li 삭제