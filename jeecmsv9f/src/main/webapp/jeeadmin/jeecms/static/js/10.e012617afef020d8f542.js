webpackJsonp([10],{"2eeV":function(n,a,t){var s=t("YUOc");"string"==typeof s&&(s=[[n.i,s,""]]),s.locals&&(n.exports=s.locals);t("FIqI")("113ad49a",s,!0,{})},DHhd:function(n,a,t){"use strict";function s(n){t("2eeV")}Object.defineProperty(a,"__esModule",{value:!0});var e=t("lcoF"),o=t("2sCs"),i=t.n(o),l=t("x1ym"),r=t("vV/h"),d={mixins:[e.a],data:function(){var n=l.a.required(),a=l.a.number();return{params:{queryUsername:"",https:""},rules:{ip:[n],name:[n],port:[n,a],encoding:[n],timeout:[n,a],username:[n],url:[n]}}},methods:{getDataInfo:function(n){var a=this;this.loading=!0,i.a.post(this.$api.ftpGet,{id:n}).then(function(t){a.loading=!1,"200"==t.code&&(a.dataInfo=t.body,a.dataInfo.password="",0==n&&(a.rules.password=[l.a.required()]),console.log(a.rules))}).catch(function(n){a.loading=!1})},update:function(){""!=this.dataInfo.password&&(this.dataInfo.password=Object(r.a)(this.dataInfo.password,"S9u978Q31NGPGc5H","X83yESM9iShLxfwS")),this.updateDataInfo(this.$api.ftpUpdate,this.dataInfo,"list")},add:function(n){""!=this.dataInfo.password&&(this.dataInfo.password=Object(r.a)(this.dataInfo.password,"S9u978Q31NGPGc5H","X83yESM9iShLxfwS")),this.saveDataInfo(n,this.$api.ftpSave,this.dataInfo,"list")}},created:function(){this.getDataInfo(this.id)}},c=function(){var n=this,a=n.$createElement,t=n._self._c||a;return t("section",{directives:[{name:"loading",rawName:"v-loading",value:n.loading,expression:"loading"}],staticClass:"cms-body"},[t("cms-back"),n._v(" "),t("el-form",{ref:"form",staticClass:"cms-form",attrs:{model:n.dataInfo,rules:n.rules,"label-width":"162px"}},[t("el-form-item",{staticClass:"flex-50",attrs:{label:"名称",prop:"name"}},[t("el-input",{staticClass:"cms-width",model:{value:n.dataInfo.name,callback:function(a){n.$set(n.dataInfo,"name",a)},expression:"dataInfo.name"}})],1),n._v(" "),t("el-form-item",{staticClass:"flex-50",attrs:{label:"服务器IP",prop:"ip"}},[t("el-input",{staticClass:"cms-width",model:{value:n.dataInfo.ip,callback:function(a){n.$set(n.dataInfo,"ip",a)},expression:"dataInfo.ip"}})],1),n._v(" "),t("el-form-item",{staticClass:"flex-50",attrs:{label:"FTP端口号",prop:"port"}},[t("el-input",{staticClass:"cms-width",model:{value:n.dataInfo.port,callback:function(a){n.$set(n.dataInfo,"port",a)},expression:"dataInfo.port"}}),n._v(" "),t("span",{staticClass:"gray"},[n._v("默认端口21")])],1),n._v(" "),t("el-form-item",{staticClass:"flex-50",attrs:{label:"传输超时时间",prop:"timeout"}},[t("el-input",{staticClass:"cms-width",model:{value:n.dataInfo.timeout,callback:function(a){n.$set(n.dataInfo,"timeout",a)},expression:"dataInfo.timeout"}}),n._v(" "),t("span",{staticClass:"gray"},[n._v("单位：秒，0为服务器默认")])],1),n._v(" "),t("el-form-item",{staticClass:"flex-50",attrs:{label:"FTP用户名",prop:"username"}},[t("el-input",{staticClass:"cms-width",model:{value:n.dataInfo.username,callback:function(a){n.$set(n.dataInfo,"username",a)},expression:"dataInfo.username"}})],1),n._v(" "),t("el-form-item",{staticClass:"flex-50",attrs:{label:"FTP密码",prop:"password"}},[t("el-input",{staticClass:"cms-width",attrs:{autocoplate:"",type:"password"},model:{value:n.dataInfo.password,callback:function(a){n.$set(n.dataInfo,"password",a)},expression:"dataInfo.password"}}),n._v(" "),t("span",{staticClass:"gray"},[n._v("留空不修改")])],1),n._v(" "),t("el-form-item",{staticClass:"flex-50",attrs:{label:"远程目录",prop:"path"}},[t("el-input",{staticClass:"cms-width",model:{value:n.dataInfo.path,callback:function(a){n.$set(n.dataInfo,"path",a)},expression:"dataInfo.path"}}),n._v(" "),t("span",{staticClass:"gray"},[n._v("留空为根目录")])],1),n._v(" "),t("el-form-item",{staticClass:"flex-50",attrs:{label:"编码",prop:"encoding"}},[t("el-input",{staticClass:"cms-width",model:{value:n.dataInfo.encoding,callback:function(a){n.$set(n.dataInfo,"encoding",a)},expression:"dataInfo.encoding"}})],1),n._v(" "),t("el-form-item",{staticClass:"flex-100",attrs:{label:"地址",prop:"url"}},[t("el-input",{staticClass:"cms-width",model:{value:n.dataInfo.url,callback:function(a){n.$set(n.dataInfo,"url",a)},expression:"dataInfo.url"}}),n._v(" "),t("span",{staticClass:"gray"},[n._v("访问该FTP的Url地址")])],1),n._v(" "),t("div",{staticClass:"form-footer"},[0==this.id?t("el-button",{directives:[{name:"perms",rawName:"v-perms",value:"/ftp/add",expression:"'/ftp/add'"}],attrs:{type:"warning"},on:{click:function(a){n.add(!0)}}},[n._v("提交并继续添加")]):n._e(),n._v(" "),0==this.id?t("el-button",{directives:[{name:"perms",rawName:"v-perms",value:"/ftp/add",expression:"'/ftp/add'"}],attrs:{type:"primary"},on:{click:function(a){n.add(!1)}}},[n._v("提交")]):n._e(),n._v(" "),0!=this.id?t("el-button",{directives:[{name:"perms",rawName:"v-perms",value:"/ftp/edit",expression:"'/ftp/edit'"}],attrs:{type:"primary"},on:{click:function(a){n.update()}}},[n._v("修改")]):n._e(),n._v(" "),t("el-button",{attrs:{type:"info"},on:{click:n.$reset}},[n._v("重置")])],1)],1)],1)},p=[],f={render:c,staticRenderFns:p},u=f,m=t("Z0/y"),v=s,I=m(d,u,!1,v,null,null);a.default=I.exports},YUOc:function(n,a,t){a=n.exports=t("UTlt")(!1),a.push([n.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",""])}});