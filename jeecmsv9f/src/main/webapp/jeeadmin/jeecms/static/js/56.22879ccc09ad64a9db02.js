webpackJsonp([56],{"+8Dd":function(t,a,e){"use strict";function s(t){e("Qmw7")}Object.defineProperty(a,"__esModule",{value:!0});var n=e("2sCs"),i=e.n(n),l={data:function(){return{dateArr:"",params:{queryModel:"day",isReplyed:""},channelParams:{parentId:"",https:"",all:""},time:"",date:{year:0,month:0,day:0},dataInfo:{},totoal:"",year:"",month:"",sum:0,tol:0,channelList:[]}},methods:{getDay:function(t,a){return new Date(t,a,0).getDate()},query:function(t){"day"===t&&(this.time=this.date.year+"-"+this.date.month+"-"+this.date.day),"month"===t&&(this.time=this.date.year+"-"+this.date.month),"year"===t&&(this.time=this.date.year),this.getDataInfo()},getDataInfo:function(){var t=this;i.a.post(this.$api.statisticGuestbookList,this.params).then(function(a){t.dataInfo=a.body.data,t.totoal=a.body.totalCount,t.sum=0;var e=0;for(var s in a.body.data)t.sum=t.sum+a.body.data[s][0],e+=a.body.data[s][0];t.tol=e,0===t.sum&&(t.sum=1)})},getChannel:function(){var t=this;i.a.post(this.$api.channelList,this.channelParams).then(function(a){t.channelList=a.body})}},created:function(){var t=new Date,a=t.getFullYear(),e=t.getMonth()+1,s=t.getDate();this.date.year=a,this.date.month=e,this.date.day=s,this.year=a,this.month=e,this.time=a+"-"+e+"-"+s,this.getDataInfo(),this.getChannel()}},o=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("section",{staticClass:"cms-body cms-chart-box"},[e("div",{staticClass:"cms-list-header",staticStyle:{"padding-left":"0"}},[e("h5",{staticClass:"data-header-title"},[t._v("留言数("+t._s(t.time)+")")]),t._v(" "),e("div",{staticClass:"flex-date"},[e("label",{staticClass:"cms-label",staticStyle:{"margin-left":"0"}},[t._v("日期：")]),t._v(" "),e("el-radio-group",{staticStyle:{"margin-right":"10px"},attrs:{size:"small"},on:{change:t.query},model:{value:t.params.queryModel,callback:function(a){t.$set(t.params,"queryModel",a)},expression:"params.queryModel"}},[e("el-radio-button",{attrs:{label:"day"}},[t._v("今日")]),t._v(" "),e("el-radio-button",{attrs:{label:"month"}},[t._v("本月")]),t._v(" "),e("el-radio-button",{attrs:{label:"year"}},[t._v("今年")])],1)],1),t._v(" "),e("div",{staticClass:"flex-date"},[e("label",{staticClass:"cms-label",staticStyle:{"margin-left":"0"}},[t._v("筛选：")]),t._v(" "),e("el-select",{staticStyle:{"margin-right":"10px"},attrs:{size:"small",placeholder:"是否回复"},on:{change:t.query},model:{value:t.params.isReplyed,callback:function(a){t.$set(t.params,"isReplyed",a)},expression:"params.isReplyed"}},[e("el-option",{attrs:{value:""}},[t._v("无")]),t._v(" "),e("el-option",{attrs:{value:!0,label:"是"}},[t._v("是")]),t._v(" "),e("el-option",{attrs:{value:!1,label:"否"}},[t._v("否")])],1)],1)]),t._v(" "),e("table",{staticClass:"data-table"},[t._m(0),t._v(" "),e("tr",[e("td",[t._v("合计")]),t._v(" "),e("td",[t._v(t._s(t.tol))]),t._v(" "),e("td")]),t._v(" "),t._l(t.dataInfo,function(a,s){return e("tr",{key:s},["day"===t.params.queryModel?e("td",[a[1]<10?e("span",[t._v("\n                                      0"+t._s(a[1])+":00-0"+t._s(a[1])+":59\n                                  ")]):e("span",[t._v("\n                                      "+t._s(a[1])+":00-"+t._s(a[1])+":59\n                                  ")])]):t._e(),t._v(" "),"month"===t.params.queryModel?e("td",[t._v("                               \n                                  "+t._s(t.year)+"-"+t._s(t.month)+"-"+t._s(a[1])+" 00:00:00-23:59:59                                \n                              ")]):t._e(),t._v(" "),"year"===t.params.queryModel?e("td",[t._v("                               \n                                   "+t._s(t.year)+"-"+t._s(a[1])+"-01——"+t._s(t.year)+"-"+t._s(a[1])+"-"+t._s(t.getDay(t.year,a[1]))+" \n                                                                 \n                              ")]):t._e(),t._v(" "),e("td",[t._v(t._s(a[0]))]),t._v(" "),e("td",[t._v(t._s((a[0]/t.sum*100).toFixed(1))+"%")])])})],2)])},r=[function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("tr",[e("th",[t._v("时段")]),t._v(" "),e("th",[t._v("留言数")]),t._v(" "),e("th",[t._v("占比")])])}],d={render:o,staticRenderFns:r},h=d,m=e("Z0/y"),_=s,c=m(l,h,!1,_,"data-v-83b432be",null);a.default=c.exports},Qmw7:function(t,a,e){var s=e("ekIU");"string"==typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);e("FIqI")("486fe308",s,!0,{})},ekIU:function(t,a,e){a=t.exports=e("UTlt")(!1),a.push([t.i,"\n.data-item[data-v-83b432be] {\n  float: left;\n  min-width: 170px;\n  margin-top: 30px;\n}\n.data-item .data-title[data-v-83b432be] {\n    font-size: 12px;\n    color: #878d99;\n    margin-bottom: 17px;\n}\n.data-item .data-num[data-v-83b432be] {\n    color: #353535;\n    font-size: 24px;\n}\n.chart-style[data-v-83b432be] {\n  width: 100%;\n  height: 380px;\n  border-bottom: 1px dashed #eee;\n  margin-bottom: 15px;\n}\n",""])}});