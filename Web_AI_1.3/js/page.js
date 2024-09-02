$(function(){
	// tab切换
	$('li.nav-item,li.dropdown-item').on('click',function(){
		var $this = $(this);
		var $iframe = $('iframe');
		$this.addClass('active').siblings().removeClass('active');
		$iframe.attr('src', $this.data('url'));
	});
})