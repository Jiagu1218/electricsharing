/*
 * loader.js用于加载所有js和css
 */
function getRootPath() {
	//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	//获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	//获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}
document.write('<script src="'+getRootPath()+'front/js/vendor/bootstrap.min.js"></script>');
document.write('<script src="'+getRootPath()+'front/js/vendor/jquery-2.2.4.min.js"></script>');
//css
document.write('<link rel="stylesheet" href="'+getRootPath()+'front/css/bootstrap.css"/>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'front/css/owl.carousel.css"/>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'front/css/linearicons.css"/>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'front/css/font-awesome.min.css"/>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'front/css/nice-select.css"/>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'front/css/magnific-popup.css"/>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'front/css/main.css"/>');


//js

document.write('<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>');

document.write('<script src="'+getRootPath()+'front/js/jquery.ajaxchimp.min.js"></script>');
document.write('<script src="'+getRootPath()+'front/js/owl.carousel.min.js"></script>');
document.write('<script src="'+getRootPath()+'front/js/jquery.nice-select.min.js"></script>');
document.write('<script src="'+getRootPath()+'front/js/jquery.magnific-popup.min.js"></script>');
document.write('<script src="'+getRootPath()+'front/js/main.js"></script>');



/*//基于boostrap定制的表格插件
document.write('<script src="'+getRootPath()+'/js/bootstrap-table.js"></script>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'/css/bootstrap-table.css"/>');
document.write('<script src="'+getRootPath()+'/js/bootstrap-table-zh-CN.js"></script>');*/
//消息提示框插件
document.write('<script src="'+getRootPath()+'/js/toastr.js"></script>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'/css/toastr.css"/>');
/*//弹出框插件（alert、confirm、dialog）
document.write('<script src="'+getRootPath()+'/js/tip.js"></script>');
//rest风格插件（包含表单数据封装）
document.write('<script src="'+getRootPath()+'/js/restful.js"></script>');
//echart图表
document.write('<script src="'+getRootPath()+'/js/echarts.common.min.js"></script>');*/
//其他组件
/*document.write('<script src="'+getRootPath()+'/js/jquery.metisMenu.js"></script>');*/
/*document.write('<script src="'+getRootPath()+'/js/jquery.backstretch.min.js"></script>');*/
document.write('<link rel="stylesheet" href="'+getRootPath()+'/css/custom-styles.css"/>');
document.write('<link rel="stylesheet" href="'+getRootPath()+'/css/font-awesome.css"/>');


//3、初始化提示框
document.write("<script>toastr.options.positionClass = 'toast-top-center';</script>")