webpackJsonp([3],{"/XBl":function(n,e,a){"use strict";function t(n){a("Eqxd")}Object.defineProperty(e,"__esModule",{value:!0});var s=a("2sCs"),l=a.n(s),r=a("x1ym"),o=a("lcoF"),i={mixins:[o.a],data:function(){var n=r.a.required("此项必填"),e=r.a.number("只能输入数字");return{ready:!1,rules:{name:[n],priority:[n,e],level:[n,e],all:[n]},roleAll:!1}},methods:{getImages:function(n){},getDataInfo:function(n){var e=this,a=this.$api;l.a.post(a.roleGet,{id:n}).then(function(n){e.loading=!1,e.dataInfo=n.body,e.ready=!0,"save"==e.type&&(e.dataInfo.all=!1),e.$refs.form.resetFields()}).catch(function(n){e.loading=!1})},getRole:function(n){this.dataInfo.perms=this.$refs.role.creatRoles()},update:function(){this.getRole(),this.updateDataInfo(this.$api.roleUpdate,this.dataInfo,"list")},add:function(n){this.getRole(),this.saveDataInfo(n,this.$api.roleSave,this.dataInfo,"list")}},created:function(){"*"===this.$store.state.perms.permsList&&(this.roleAll=!0),this.getDataInfo(this.id)}},c=function(){var n=this,e=n.$createElement,a=n._self._c||e;return a("section",{directives:[{name:"loading",rawName:"v-loading",value:n.loading,expression:"loading"}],staticClass:"cms-body"},[a("cms-back"),n._v(" "),a("el-form",{ref:"form",staticClass:"cms-form",attrs:{model:n.dataInfo,rules:n.rules,"label-width":"162px"}},[a("el-form-item",{staticClass:"flex-50",attrs:{label:"角色名",prop:"name"}},[a("el-input",{staticClass:"cms-width",model:{value:n.dataInfo.name,callback:function(e){n.$set(n.dataInfo,"name",e)},expression:"dataInfo.name"}})],1),n._v(" "),a("el-form-item",{staticClass:"flex-50",attrs:{label:"排列顺序",prop:"priority"}},[a("el-input",{staticClass:"cms-width",attrs:{type:"number"},model:{value:n.dataInfo.priority,callback:function(e){n.$set(n.dataInfo,"priority",e)},expression:"dataInfo.priority"}})],1),n._v(" "),a("el-form-item",{staticClass:"flex-50",attrs:{label:"等级",prop:"level"}},[a("el-input",{staticClass:"cms-width",attrs:{type:"number"},model:{value:n.dataInfo.level,callback:function(e){n.$set(n.dataInfo,"level",e)},expression:"dataInfo.level"}})],1),n._v(" "),a("el-form-item",{staticClass:"flex-50",attrs:{label:"拥有所有权限",prop:"all"}},[a("el-radio-group",{attrs:{disabled:!n.roleAll},model:{value:n.dataInfo.all,callback:function(e){n.$set(n.dataInfo,"all",e)},expression:"dataInfo.all"}},[a("el-radio",{attrs:{label:!0}},[n._v("是")]),n._v(" "),a("el-radio",{attrs:{label:!1}},[n._v("否")])],1),n._v(" "),n.roleAll?n._e():a("span",{staticClass:"gray"},[n._v("非超级管理员不能勾选该选项")])],1),n._v(" "),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:!n.dataInfo.all,expression:"!dataInfo.all"}],staticClass:"flex-100",attrs:{label:"拥有所有权限"}},[a("span",{staticClass:"gray"},[n._v("勾选菜单操作时，请尽量选择列表菜单，以避免未知问题")]),n._v(" "),n.ready?a("cms-role",{ref:"role",attrs:{perms:n.dataInfo.perms}}):n._e()],1),n._v(" "),a("div",{staticClass:"form-footer"},[n.isType("save")?a("el-button",{directives:[{name:"perms",rawName:"v-perms",value:"/role/save",expression:"'/role/save'"}],attrs:{type:"warning"},on:{click:function(e){n.add(!0)}}},[n._v("保存并继续添加")]):n._e(),n._v(" "),n.isType("save")?a("el-button",{directives:[{name:"perms",rawName:"v-perms",value:"/role/save",expression:"'/role/save'"}],attrs:{type:"primary"},on:{click:function(e){n.add(!1)}}},[n._v("提交")]):n._e(),n._v(" "),n.isType("update")?a("el-button",{directives:[{name:"perms",rawName:"v-perms",value:"/role/update",expression:"'/role/update'"}],attrs:{type:"primary"},on:{click:function(e){n.update()}}},[n._v("修改")]):n._e(),n._v(" "),a("el-button",{attrs:{type:"info"},on:{click:n.$reset}},[n._v("重置")]),n._v(" "),n.isType("save")?a("span",{staticClass:"gray"},[n._v("选择“提交并继续添加”按钮会停留在添加页面；选择“提交”按钮后将跳转到对应的列表页")]):n._e()],1)],1)],1)},d=[],p={render:c,staticRenderFns:d},f=p,m=a("Z0/y"),v=t,u=m(i,f,!1,v,null,null);e.default=u.exports},Eqxd:function(n,e,a){var t=a("uszS");"string"==typeof t&&(t=[[n.i,t,""]]),t.locals&&(n.exports=t.locals);a("FIqI")("47c28595",t,!0,{})},uszS:function(n,e,a){e=n.exports=a("UTlt")(!1),e.push([n.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",""])}});