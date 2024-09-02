$(function(){
	//知识点内容展示隐藏
	$('.ktitle').click(function(){		
			$(this).parent().next('.kdetails').fadeToggle();
			if($(this).text()=="show more"){
				$(this).text('折叠内容').removeClass('badge-light').addClass('badge-danger');
			}else{
				$(this).text('show more').removeClass('badge-danger').addClass('badge-light');}
	});
	//拓展答案显示切换
	$('#tips').click(function(){
		if($('.answer').is(':hidden')){
			$('.answer').show('slow');
		}else{
			$('.answer').hide('slow');
		}
		if($(this).text()=="点击查看答案"){
			$(this).text('隐藏答案').removeClass('btn-success').addClass('btn-danger');
		}else{
			$(this).text('点击查看答案').removeClass('btn-danger').addClass('btn-success');}
	});
	//复制代码
	$('button.copyBtn').click(function(){
		//if($('.program').is(':hidden')){$('.program').show();}		
		var procode=$(this).prev().html();
		let regex2 = /&nbsp;/g; // 使用全局正则表达式
		let newStr2 = procode.replace(regex2, " ");
		let regex = /<br>/g; // 使用全局正则表达式
		let newStr = newStr2.replace(regex, "\n");
		var txt=$('#pro').text();
		if(txt==''){
			$('#pro').text(newStr);
		}else{
			
			$('#pro').text(txt+'\n'+newStr);
		}		
	});
	//清空代码
	$('#pro-clear').click(function(){
		$('#pro').html('');
	});
	
	//运行窗口的移动
	var x = 10;
		var y = 20;
		$(".copyBtn").mouseover(function(e){												 
			$(".program")
				.css({
					"top": (e.pageY+y) + "px",
					"left":  (e.pageX+x)  + "px"
				}).show("fast");//设置x坐标和y坐标，并且显示
	    }).mousemove(function(e){
			$(".program")
				.css({
					"top": (e.pageY+y) + "px",
					"left":  (e.pageX+x)  + "px"
				});
		});
		
		//关闭运行窗口
		$('.close').click(function(){
			$('.program').hide('slow');
		})
})
//电梯导航
$(function(){
	//头部标题固定在页面顶部
	/* $(window).scroll(function(){
		if($(window).scrollTop()>=100){
			$('.hnav').addClass('fixedNav');
		}else{
			$('.hnav').removeClass('fixedNav');
		}			
	}); */
	//垂直导航显示隐藏
	$(window).on('scroll',function(){
		var $scroll=$(this).scrollTop();
		//console.log('$scroll',$scroll);
		if($scroll>=100){
			$('#right_nav').show();
		}else{
			$('#right_nav').hide();
		}
		//拖动滚轮，点亮对应楼层标签
		/* $('.son_wrap').each(function(){
			//console.log('son_wrap',$('.son_wrap').eq($(this).index()));
			var $son_wraptop=$(this).offset().top;
			console.log('$son_wraptop',$son_wraptop);			
			if($son_wraptop<$scroll){
				$('#right_nav li').removeClass('active');
				$('#right_nav li').eq($(this).index()).addClass('active');
				return false
			}
		}); */
	});
	//点击楼层导航到对应内容
	var $son_wrapli=$('#right_nav li').not('.last');
	$son_wrapli.on('click',function(){
		$(this).addClass('active').siblings('li').removeClass('active');
		var $son_wraptop=$('.son_wrap').eq($(this).index()).offset().top;
		$('html,body').animate({scrollTop:$son_wraptop})
	});
	//回到顶部
	$('.last').on('click',function(){
		$('html,body').animate({scrollTop:0})
	})
});