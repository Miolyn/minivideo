webpackJsonp([1],{0:function(e,t){},Bqkj:function(e,t){},NCmE:function(e,t){},NHnr:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=r("7+uW"),s={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},staticRenderFns:[]};var n=r("VU/8")({name:"App"},s,!1,function(e){r("NCmE")},null,null).exports,o=r("/ocq"),i={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"hello"},[r("h1",[e._v(e._s(e.msg))]),e._v(" "),r("h2",[e._v("Essential Links")]),e._v(" "),e._m(0),e._v(" "),r("h2",[e._v("Ecosystem")]),e._v(" "),e._m(1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("ul",[r("li",[r("a",{attrs:{href:"https://vuejs.org",target:"_blank"}},[e._v("\n        Core Docs\n      ")])]),e._v(" "),r("li",[r("a",{attrs:{href:"https://forum.vuejs.org",target:"_blank"}},[e._v("\n        Forum\n      ")])]),e._v(" "),r("li",[r("a",{attrs:{href:"https://chat.vuejs.org",target:"_blank"}},[e._v("\n        Community Chat\n      ")])]),e._v(" "),r("li",[r("a",{attrs:{href:"https://twitter.com/vuejs",target:"_blank"}},[e._v("\n        Twitter\n      ")])]),e._v(" "),r("br"),e._v(" "),r("li",[r("a",{attrs:{href:"http://vuejs-templates.github.io/webpack/",target:"_blank"}},[e._v("\n        Docs for This Template\n      ")])])])},function(){var e=this.$createElement,t=this._self._c||e;return t("ul",[t("li",[t("a",{attrs:{href:"http://router.vuejs.org/",target:"_blank"}},[this._v("\n        vue-router\n      ")])]),this._v(" "),t("li",[t("a",{attrs:{href:"http://vuex.vuejs.org/",target:"_blank"}},[this._v("\n        vuex\n      ")])]),this._v(" "),t("li",[t("a",{attrs:{href:"http://vue-loader.vuejs.org/",target:"_blank"}},[this._v("\n        vue-loader\n      ")])]),this._v(" "),t("li",[t("a",{attrs:{href:"https://github.com/vuejs/awesome-vue",target:"_blank"}},[this._v("\n        awesome-vue\n      ")])])])}]};var l=r("VU/8")({name:"HelloWorld",data:function(){return{msg:"Welcome to Your Vue.js App"}}},i,!1,function(e){r("OVGu")},"data-v-d8ec41bc",null).exports,u=r("fZjL"),c=r.n(u);function m(e){if(document.cookie.length>0){var t=document.cookie.indexOf(e+"=");if(-1!=t){t=t+e.length+1;var r=document.cookie.indexOf(";",t);return-1==r&&(r=document.cookie.length),unescape(document.cookie.substring(t,r))}}return""}function p(e){!function(e,t,r){var a=new Date;a.setSeconds(a.getSeconds()+r),document.cookie=e+"="+escape(t)+"; expires="+a.toGMTString(),console.log(document.cookie)}(e,"",-1)}var v=r("mtWM"),g=r.n(v),d=r("mw3O"),f=r.n(d);a.default.prototype.$qs=f.a;var h="http://124.70.31.157:8090",_={name:"Login",data:function(){var e=this;return{two:!0,loginParam:{},registerParam:{},rules:{username:[{required:!0,message:"请输入用户名",trigger:"blur"},{min:6,max:20,message:"请输入6-20位字符",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"},{min:6,max:20,message:"请输入6-20位字符",trigger:"blur"}],r_password:[{required:!0,message:"请输入确认密码",trigger:"blur"},{validator:function(t,r,a){""===r?a(new Error("请再次输入密码")):r!==e.registerParam.password?a(new Error("两次输入密码不一致!")):a()},trigger:"blur"}],email:[{required:!0,message:"请输入邮箱",trigger:"blur"},{type:"email",message:"请输入正确电子邮件地址",trigger:"blur"}]}}},mounted:function(){m("username")&&this.$router.push("/home")},methods:{submitLoginForm:function(e){var t=this;this.$refs[e].validate(function(e){if(!e)return t.$message.error("请输入账号和密码"),!1;var r;(r=t.loginParam,g.a.post(h+"/user/login",r)).then(function(e){sessionStorage.clear(),t.$message.success("登录成功"),t.$router.push("/hello")}).catch(function(e){t.$message.error("用户名或密码错误"),console.log(e)})})},submitRegisterForm:function(e){var t=this;this.$refs[e].validate(function(e){if(!e)return t.$message.error("请根据提示输入必填项"),!1;var r;(r=t.registerParam,g.a.post(h+"/user/register",r)).then(function(e){t.$message.success("注册成功"),t.loginParam.username=t.registerParam.username,t.loginParam.password=t.registerParam.password,t.two=!0}).catch(function(e){var r=c()(e.response.data.details)[0];t.$message.error(e.response.data.details[r][0])})})},goFindPwd:function(){this.$message.error("功能未开通")}}},b={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"login-wrap"},[e.two?r("div",{staticClass:"ms-login"},[r("div",{staticClass:"ms-title"},[e._v("加壹视频")]),e._v(" "),r("el-form",{ref:"loginForm",staticClass:"ms-content",attrs:{model:e.loginParam,rules:e.rules,"label-width":"0px"}},[r("el-form-item",{attrs:{prop:"username"}},[r("el-input",{attrs:{placeholder:"用户名","prefix-icon":"el-icon-user"},model:{value:e.loginParam.username,callback:function(t){e.$set(e.loginParam,"username",t)},expression:"loginParam.username"}})],1),e._v(" "),r("el-form-item",{attrs:{prop:"password"}},[r("el-input",{attrs:{type:"password",placeholder:"密码","prefix-icon":"el-icon-lock"},model:{value:e.loginParam.password,callback:function(t){e.$set(e.loginParam,"password",t)},expression:"loginParam.password"}})],1),e._v(" "),r("div",{staticClass:"login-btn"},[r("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitLoginForm("loginForm")}}},[e._v("登录")])],1),e._v(" "),r("el-link",{staticStyle:{"text-align":"center"},attrs:{type:"primary"},on:{click:function(t){e.two=!1}}},[e._v("去注册 >")]),e._v(" "),r("el-link",{staticStyle:{"text-align":"center",float:"right"},attrs:{type:"primary"},on:{click:function(t){return e.goFindPwd()}}},[e._v("找回密码？")])],1)],1):r("div",{staticClass:"ms-login"},[r("div",{staticClass:"ms-title"},[e._v("加壹视频")]),e._v(" "),r("el-form",{ref:"registerForm",staticClass:"ms-content",attrs:{model:e.registerParam,rules:e.rules,"label-width":"0px"}},[r("el-form-item",{attrs:{prop:"username"}},[r("el-input",{attrs:{placeholder:"用户名","prefix-icon":"el-icon-user"},model:{value:e.registerParam.username,callback:function(t){e.$set(e.registerParam,"username",t)},expression:"registerParam.username"}})],1),e._v(" "),r("el-form-item",{attrs:{prop:"password"}},[r("el-input",{attrs:{type:"password",placeholder:"密码","prefix-icon":"el-icon-lock"},model:{value:e.registerParam.password,callback:function(t){e.$set(e.registerParam,"password",t)},expression:"registerParam.password"}})],1),e._v(" "),r("el-form-item",{attrs:{prop:"r_password"}},[r("el-input",{attrs:{type:"password",placeholder:"确认密码","prefix-icon":"el-icon-lock"},model:{value:e.registerParam.r_password,callback:function(t){e.$set(e.registerParam,"r_password",t)},expression:"registerParam.r_password"}})],1),e._v(" "),r("div",{staticClass:"login-btn"},[r("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitRegisterForm("registerForm")}}},[e._v("注册")])],1),e._v(" "),r("el-link",{staticStyle:{"text-align":"center"},attrs:{type:"primary"},on:{click:function(t){e.two=!0}}},[e._v("去登录 >")])],1)],1)])},staticRenderFns:[]};var w=r("VU/8")(_,b,!1,function(e){r("zGCb")},"data-v-00b45b98",null).exports,k={render:function(){var e=this.$createElement;return(this._self._c||e)("div")},staticRenderFns:[]};var $=r("VU/8")({name:"Main"},k,!1,function(e){r("Bqkj")},"data-v-3f50fd5b",null).exports,P={name:"Home",data:function(){return{name:""}},mounted:function(){var e=m("username");this.name=e,""==e&&this.$router.push("/")},methods:{quit:function(){p("username")}}},x={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",[t("h3",[this._v("欢迎 "+this._s(this.name))]),this._v(" "),t("a",{attrs:{href:"#"},on:{click:this.quit}},[this._v("注销登录")])])},staticRenderFns:[]};var y=r("VU/8")(P,x,!1,function(e){r("cJd/")},"data-v-16892c19",null).exports;a.default.use(o.a);var j=new o.a({routes:[{path:"/",name:"Login",component:w},{path:"/main",name:"Main",component:$},{path:"/home",name:"Home",component:y},{path:"/hello",name:"HelloWorld",component:l}]}),C=r("zL8q"),F=r.n(C),q=(r("tvR6"),r("//Fk")),E=r.n(q),R=this;g.a.interceptors.request.use(function(e){var t=sessionStorage.getItem("token")||localStorage.getItem("token");return t&&(e.headers.Authorization="Token "+t),e},function(e){return E.a.reject(e)}),g.a.interceptors.response.use(void 0,function(e){var t=e.response;if(!t)return E.a.reject(e);switch(t.status){case 401:R.$message.error("未登录"),j.push({path:"/"});case 403:R.$message.error("没有该操作权限"),j.push({name:"403"});case 500:R.$message.error("服务器错误")}return E.a.reject(e.response.data)}),a.default.use(F.a),a.default.prototype.$axios=g.a,a.default.config.productionTip=!1,a.default.prototype.$qs=f.a,new a.default({el:"#app",router:j,components:{App:n},template:"<App/>"})},OVGu:function(e,t){},"cJd/":function(e,t){},tvR6:function(e,t){},zGCb:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.fdfc5215a6f2f0e8330a.js.map