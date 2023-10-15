<template>
  <div class="login_container">
    <div class="login_box">
      <div class="logo1">
        <img src="../../assets/logoMy.png" />
      </div>
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../../assets/lockpicture.png" alt="" />
      </div>
      <!-- 登录表单区域 -->
      <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="loginFormRules"
        label-width="0px"
        class="login_form"
      >
        <!-- titlw -->
        <el-form-item>
          <!-- h2已经修饰，其他不能修饰 -->
          <span
            ><font id="titleName" style="color: white; font-size: 15px"
              >攀枝花学院实验耗材采购系统</font
            ></span
          >
        </el-form-item>
        <!-- 登录选择 -->
        <el-form-item label-width="10px">
          <el-select v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 用户名 -->
        <el-form-item prop="name" label-width="10px">
          <el-input
            v-model="loginForm.name"
            prefix-icon="iconfont icon-user"
          ></el-input>
        </el-form-item>
        <!-- 密码框 -->
        <el-form-item prop="pwd" label-width="10px">
          <el-input
            v-model="loginForm.pwd"
            prefix-icon="iconfont icon-3702mima"
            type="password"
          ></el-input>
        </el-form-item>
        <!--按钮区-->
        <el-form-item class="btns">
          <el-button type="primary" @click="login('loginForm')">登录</el-button>
          <el-button type="info" @click="restLoginForm('loginForm')"
            >重置</el-button
          >
          <p>没有账号?点击<a href="#" @click="enRegister()">注册</a></p>
        </el-form-item>
      </el-form>
    </div>

    <div class="board_container">
      <div>
        <font class="title" style="font-size: 30px; color: white">公告栏</font>
        <el-input v-model="boardName" placeholder="请输入公告名进行搜索" ></el-input>
      </div>
      <el-table
        :data="List1"
        style="width: 100%; font-size: 15px"
        max-height="330"
        :default-sort="{ prop: 'start_time', order: 'descending' }"
        @row-click="showDialog"
        class="abc"
      >
        <el-table-column type="index"></el-table-column>
        <el-table-column label="公告名" width="200px" prop="name">
        </el-table-column>
        <el-table-column label="商品类别" prop="category"></el-table-column>
        <el-table-column label="发布时间" prop="start_time"></el-table-column>
      </el-table>
      <!-- <el-link  @click="test()">主要链接</el-link> -->
    </div>

   <el-dialog
      title="采购公告"
      :visible.sync="showDialogVisible"
      width="50%"
      fullscreen
      @close="showDialogClosed"
      class="dialog"
    >
    <div>
        <div class="tit" style="font-size: 25px;" > {{ clickTarget.name}}</div>
        <div class="tit" style="font-size: 20px; margin-top: 20px"></div>
        <!-- {{ List[0].publish_time }} -->
        <div class="tit">
          发布时间：
          {{ this.$moment(clickTarget.publish_time).format("YYYY-MM-DD") }}
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div id="vsb_content" class="content">
          <p><span style="font-size: 16px"></span></p>
          <!-- <p class="MsoNormal" style="margin: 0cm 0cm 0px;text-align: center;line-height: 35px;mso-line-height-rule: exactly"><span style="color: rgb(51, 51, 51);font-family: 黑体;font-size: 24px;">攀枝花学院废旧铁窗处置拍卖公告<span > -->
          <!--?xml:namespace prefix="o" ns="urn:schemas-microsoft-com:office:office" /-->

          <!-- </span></p> -->
          <p><span style="font-size: 16px"></span></p>
          <p
            class="MsoNormal"
            style="
              margin: 0cm 0cm 0px;
              text-align: left;
              line-height: 35px;
              mso-line-height-rule: exactly;
            "
          >
            <span
              lang="EN-US"
              style="color: rgb(51, 51, 51); font-family: 黑体; font-size: 24px"
            >
            </span>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p
            class="MsoNormal"
            style="
              margin: 0cm 0cm 0px;
              text-align: left;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
            "
          >
            <span
              style="color: rgb(51, 51, 51); font-family: 宋体; font-size: 20px"
              >{{ clickTarget.title }} </span
            ><span
              lang="EN-US"
              style="
                color: rgb(51, 51, 51);
                font-family: 'ˎ̥', 'serif';
                font-size: 20px;
              "
            >
              <br /><span style="font-family: 宋体">
                <strong style="mso-bidi-font-weight: normal">
                  <span style="mso-spacerun: yes"> &nbsp; </span></strong
                ></span
              > </span
            ><strong style="mso-bidi-font-weight: normal">
              <span
                style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                "
              >
                一、采购目的</span
              ></strong
            >
            <strong style="mso-bidi-font-weight: normal">
              <span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
              >
              </span
            ></strong>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p
            class="MsoNormal"
            style="
              margin: 0cm 0cm 0px;
              text-align: left;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
            "
          >
            <span
              style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 21px;
                mso-ascii-theme-font: minor-fareast;
                mso-fareast-theme-font: minor-fareast;
                mso-hansi-theme-font: minor-fareast;
              "
              >{{ clickTarget.reason
              }}<strong style="mso-bidi-font-weight: normal"
                ><span lang="EN-US"> </span></strong
            ></span>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p
            class="MsoNormal"
            style="
              margin: 0cm 0cm 0px;
              text-align: left;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
            "
          >
             
            <strong style="mso-bidi-font-weight: normal"
              ><span
                style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                "
                >二、采购详情</span
              ></strong
            ><br>
            <span
              style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              "
              >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、发布学院:</span
            >
            <span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
                >{{
                 clickTarget.academy
                }}</span>
            <br><span
              style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              "
              >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、采购需求:</span
            ><span style="font-family: 宋体"
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
                >{{
                  clickTarget.product_name
                }}</span
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
                >（{{
                  clickTarget.purpose
                }}）</span
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
              >
              </span
            ></span>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p
            class="MsoNormal"
            style="
              margin: 0cm 0cm 0px;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
              mso-char-indent-count: 2;
            "
          >
            <strong style="mso-bidi-font-weight: normal"
              ><span
                style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                "
                >三、竞拍要求</span
              ></strong
            ><strong style="mso-bidi-font-weight: normal"
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
                ><br /><span style="font-family: 宋体"></span></span></strong
            ><span style="font-family: 宋体"
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
                ><span style="mso-spacerun: yes"> &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; </span
                ><strong style="mso-bidi-font-weight: normal">1</strong></span
              ><strong style="mso-bidi-font-weight: normal"
                ><span
                  style="
                    color: rgb(51, 51, 51);
                    font-family: 宋体;
                    font-size: 20px;
                    mso-ascii-font-family: ˎ̥;
                  "
                  >、竞拍资格：</span
                ></strong
              ><span
                style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                "
                >{{ clickTarget.qualifications }}</span
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
              >
              </span
            ></span>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p
            class="MsoNormal"
            style="
              margin: 0cm 0cm 0px;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
              mso-char-indent-count: 2;
            "
          >
            <span style="font-family: 宋体"
              ><strong style="mso-bidi-font-weight: normal"
                ><span
                  lang="EN-US"
                  style="
                    color: rgb(51, 51, 51);
                    font-family: 'ˎ̥', 'serif';
                    font-size: 20px;
                  "
                  >&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; 2</span
                ></strong
              ><strong style="mso-bidi-font-weight: normal"
                ><span
                  style="
                    color: rgb(51, 51, 51);
                    font-family: 宋体;
                    font-size: 20px;
                    mso-ascii-font-family: ˎ̥;
                  "
                  >、竞拍报名</span
                ></strong
              ></span
            ><span
              lang="EN-US"
              style="
                color: rgb(51, 51, 51);
                font-family: 'ˎ̥', 'serif';
                font-size: 20px;
              "
              ><br /><span style="font-family: 宋体"
                ><span style="mso-spacerun: yes">&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; </span></span
              ></span
            ><span
              style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              "
              >1）、截止时间：</span
            ><span style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              "><strong>{{
              this.$moment(clickTarget.end_time).format("YYYY-MM-DD")
            }}</strong></span>
            <a name="_GoBack"></a><br />
            <span style="font-family: 宋体"
              ><span style="mso-spacerun: yes"> &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;  &nbsp;&nbsp; </span></span
            >
            <span
              style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              "
              >2）、报名方式：使用网站申请或者直接到学校进行申请</span
            ><span style="font-family: 宋体"
              >{{ clickTarget.auction_site }} </span
            ><span
              lang="EN-US"
              style="
                color: rgb(51, 51, 51);
                font-family: 'ˎ̥', 'serif';
                font-size: 20px;
              "
              ><br /><span style="font-family: 宋体"
                ><span style="mso-spacerun: yes"> &nbsp;  &nbsp;&nbsp; &nbsp;</span
                ><strong style="mso-bidi-font-weight: normal"
                  ><span style="mso-spacerun: yes"> </span>3</strong
                ></span
              ></span
            ><strong style="mso-bidi-font-weight: normal"
              ><span
                style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                "
                >、保证金：</span
              ></strong
            ><span
              style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              "
              >参与投标商在开拍前需缴纳现金保证金</span
            ><span style="font-family: 宋体"
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
                >2000</span
              ><span
                style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                "
                >元。未中标者当场退还保证金，若中标者弃标保证金不予退还。所有物资（含待拆除的学生课桌椅）</span
              ></span
            ><span
              style="
                color: black;
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
                mso-themecolor: text1;
              "
              >搬运完成、垃圾清运完毕、建筑物破坏恢复后退还保证金。</span
            ><span
              lang="EN-US"
              style="
                color: black;
                font-family: 'ˎ̥', 'serif';
                font-size: 20px;
                mso-themecolor: text1;
              "
              ><br /><span style="font-family: 宋体"></span></span
            ><span style="font-family: 宋体"
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
                ><span style="mso-spacerun: yes"> &nbsp;</span
                ><span style="mso-spacerun: yes"> </span></span
              ><span
                lang="EN-US"
                style="
                  color: black;
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                  mso-themecolor: text1;
                "
                ><span style="mso-spacerun: yes"> </span
                ><strong style="mso-bidi-font-weight: normal"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;四</strong></span
              ><strong style="mso-bidi-font-weight: normal"
                ><span
                  style="
                    color: black;
                    font-family: 宋体;
                    font-size: 20px;
                    mso-ascii-font-family: ˎ̥;
                    mso-themecolor: text1;
                  "
                  >、成交原则：</span
                ></strong
              ><span
                style="
                  color: black;
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                  mso-themecolor: text1;
                "
                >{{ clickTarget.rules }}</span
              ><span
                lang="EN-US"
                style="
                  color: black;
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                  mso-themecolor: text1;
                "
              >
              </span
            ></span>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p
            class="MsoNormal"
            style="
              margin: 0cm 0cm 0px;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
              mso-char-indent-count: 2;
            "
          >
            <span style="font-family: 宋体"
              ><span
                lang="EN-US"
                style="
                  color: black;
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                  mso-themecolor: text1;
                "
                > 五</span
              ><span
                style="
                  color: black;
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                  mso-themecolor: text1;
                "
                >、其他事宜：</span
              ></span
            ><span
              style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              "
              >{{ clickTarget.others }}</span
            >
          <p
            class="MsoNormal"
            style="
              margin: 0cm 0cm 0px;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
              mso-char-indent-count: 1.98;
            "
          >
          
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p
            class="MsoNormal"
            style="
              margin: 0cm 0cm 0px;
              line-height: 35px;
              text-indent: 32px;
              mso-line-height-rule: exactly;
              mso-char-indent-count: 1.98;
            "
          >
            <span
              lang="EN-US"
              style="
                color: rgb(51, 51, 51);
                font-family: 'ˎ̥', 'serif';
                font-size: 16px;
                mso-bidi-font-size: 13px;
              "
              ><br /><span style="font-family: 宋体"></span></span
            ><span style="font-family: 宋体"
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
              >
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp;</span
              ><span
                style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                "
              ></span
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
              >
                <span style="mso-spacerun: yes">
                  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                  &nbsp;</span
                >
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp;&nbsp; &nbsp;</span
              ><span
                style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                "
                > &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;攀枝花学院</span
              ></span
            ><span
              lang="EN-US"
              style="
                color: rgb(51, 51, 51);
                font-family: 'ˎ̥', 'serif';
                font-size: 20px;
              "
              ><br /></span>
          </p>
          <p><span style="font-size: 16px"></span></p>
        </div>
        <div id="div_vote_id"></div>
      </div>
    </el-dialog>
    <el-dialog
      title="结果公告"
      :visible.sync="showDialogVisible2"
      width="50%"
      fullscreen
      @close="showDialogClosed"
      class="dialog"
    >
      <div class="Section0" style="layout-grid: 15.6pt">
        <p class="MsoNormal" align="center" style="text-align: center">
          <b
            ><span
              style="
                mso-spacerun: 'yes';
                font-family: 宋体;
                mso-ascii-font-family: Calibri;
                mso-hansi-font-family: Calibri;
                mso-bidi-font-family: 'Times New Roman';
                font-weight: bold;
                font-size: 15pt;
                mso-font-kerning: 1pt;
              "
              ><font face="宋体">{{ clickTarget.purchase_name }}</font></span
            ></b
          ><b
            ><span
              style="
                mso-spacerun: 'yes';
                font-family: Calibri;
                mso-fareast-font-family: 宋体;
                mso-bidi-font-family: 'Times New Roman';
                font-weight: bold;
                font-size: 15pt;
                mso-font-kerning: 1pt;
              "
              ><o:p></o:p></span
          ></b>
        </p>
        <p class="MsoNormal" align="center" style="text-align: center">
          <span
            style="
              mso-spacerun: 'yes';
              font-family: 宋体;
              mso-ascii-font-family: Calibri;
              mso-hansi-font-family: Calibri;
              mso-bidi-font-family: 'Times New Roman';
              font-size: 10.5pt;
              mso-font-kerning: 1pt;
            "
            ><font face="Calibri">{{ clickTarget.publish_time }}</font
            ><font face="Calibri">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font
            ><font face="宋体">资管处</font
            ><font face="Calibri">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font
            ><font face="宋体">资管处装备科</font
            ><font face="Calibri">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font
            ><font face="宋体">点击数：</font
            ><font face="Calibri">{{ clickTarget.click_count }}</font></span
          ><span
            style="
              mso-spacerun: 'yes';
              font-family: 宋体;
              mso-ascii-font-family: Calibri;
              mso-hansi-font-family: Calibri;
              mso-bidi-font-family: 'Times New Roman';
              font-size: 10.5pt;
              mso-font-kerning: 1pt;
            "
            ><o:p></o:p
          ></span>
        </p>
        <p
          class="MsoNormal"
          style="text-indent: 21pt; mso-char-indent-count: 2"
        >
          <span
            style="
              mso-spacerun: 'yes';
              font-family: 宋体;
              mso-ascii-font-family: Calibri;
              mso-hansi-font-family: Calibri;
              mso-bidi-font-family: 'Times New Roman';
              font-size: 10.5pt;
              mso-font-kerning: 1pt;
            "
            ><font face="宋体"
              >采购原因为{{ clickTarget.name }}，{{
                clickTarget.reason
              }}，采购预算价为</font
            ><font face="Calibri">{{ clickTarget.highest_price }}</font
            ><font face="宋体"
              >元，拟采购供应商{{ clickTarget.company_name }}，中标价：</font
            ><font face="Calibri">{{ clickTarget.realy_price }}</font
            ><font face="宋体">元，现予以公示，采购审批表等见附件</font></span
          ><span
            style="
              mso-spacerun: 'yes';
              font-family: 宋体;
              mso-ascii-font-family: Calibri;
              mso-hansi-font-family: Calibri;
              mso-bidi-font-family: 'Times New Roman';
              font-size: 10.5pt;
              mso-font-kerning: 1pt;
            "
            ><o:p></o:p
          ></span>
        </p>
        <p class="MsoNormal">
          <span
            style="
              mso-spacerun: 'yes';
              font-family: 宋体;
              mso-ascii-font-family: Calibri;
              mso-hansi-font-family: Calibri;
              mso-bidi-font-family: 'Times New Roman';
              font-size: 10.5pt;
              mso-font-kerning: 1pt;
            "
            ><font face="宋体">附件【</font></span
          ><span
            ><a @click="downLoad"
              ><span
                style="
                  mso-spacerun: 'yes';
                  font-family: 宋体;
                  mso-ascii-font-family: Calibri;
                  mso-hansi-font-family: Calibri;
                  mso-bidi-font-family: 'Times New Roman';
                  font-size: 10.5pt;
                  mso-font-kerning: 1pt;
                "
                ><font face="宋体"
                  >{{ clickTarget.product_name }}采购审批表</font
                ><font face="Calibri">.pdf</font></span
              ></a
            ></span
          ><span
            style="
              mso-spacerun: 'yes';
              font-family: 宋体;
              mso-ascii-font-family: Calibri;
              mso-hansi-font-family: Calibri;
              mso-bidi-font-family: 'Times New Roman';
              font-size: 10.5pt;
              mso-font-kerning: 1pt;
            "
            ><font face="宋体">】</font></span
          >
        </p>

<br>
<br>
<br>
<br>
<el-tag style="font-size: 20px;">以下是出价详情：</el-tag>
        <el-table :data="resultList" style="width: 100%;font-size: 15px;" max-height="300px" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="公司名称" prop="tag"></el-table-column>
        <el-table-column label="申请产品" prop="product_name"></el-table-column>
        <el-table-column label="出价" prop="realy_price"></el-table-column>
        </el-table>



      </div>
    </el-dialog>
  
  </div>
</template>
<script>
import { default as BoardJs } from "./Board";
export default {
  data() {
    return {
      boardName:'',
      resultList:[],
      List1:[],
      currentPage: 1, //初始页
      pagesize: 5, //    每页的数据

      applyDialog: false,
      addForm: {},
      tmpId: "",

      types: [
        { name: "结果公告", id: 2 },
        { name: "招标公告", id: 1 },
      ],
      type_value: "",
      productName: 0,
      currentPage: 0,
      date: "",
      AcademysMap: [],
      CategoriesMap: [],
      List: [],
      clickTarget: {},
      count1: 0,
      showDialogVisible: false,
      showDialogVisible1: false,
      showDialogVisible2: false,
      purchaseVisible: false,
      applyForBoardList: [],
      selects: {
        academy: "",
        categories: "",
        value1: [new Date(), new Date()],
        type: "",
      },

      // 这是数据表单的数据绑定对象
      loginForm: {
        name: "nihao",
        pwd: "123456",
      },
      value: "0",
      options: [
        {
          value: "0",
          label: "供应商",
        },
        {
          value: "1",
          label: "管理员",
        },
      ],
      // 这是登录表单的验证规则对象
      loginFormRules: {
        // 验证用户名是否合法
        name: [
          { required: true, message: "请输入登录名称", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度应该在3到10个字符之间",
            trigger: "blur",
          },
        ],
        // 验证密码是否合法
        pwd: [
          { required: true, message: "请输入登陆密码", trigger: "blur" },
          {
            min: 6,
            max: 15,
            message: "长度在6到15个字符之间",
            trigger: "blur",
          },
        ],
      },
    };
  },
  watch:{
    boardName(val,oldval){
        console.log("hello");
        this.search1();
    }
  },
  mounted() {
    this.inital();
  },
  methods: {
    search1(){
      if(this.boardName.length==0||this.boardName==null)
        {
          this.List1=this.List;
          return ;
        }
        console.log("search");
        this.List1=[];
        this.dealSearch();
        
    },
    dealSearch(){
        for(let i=0;i<this.List.length;i++){
          if(this.List[i].name.indexOf(this.boardName)!=-1){
            this.List1.push(this.List[i]);
          }
        }
    },
      async getApplyList(id){
 const { data: res } = await this.$http.get(
        "getApplyList?id=" + id
        );
        this.resultList =res.date;
    },
    inital() {
      this.getAcademyList().then((val) => {
        this.getCategoriesList().then((val) => {
          this.getBoardList();
        });
      });
      this.getApplyBoard();
    },

    showAddDialog(val) {
      this.$message.warning("注册之后才能申请");
    },
    async addApplication() {
      // 隐藏添加用户的对话框
      this.addDialogVisible = false;
      const data = JSON.parse(window.sessionStorage.getItem("data"));
      this.addForm.sid = data.id;
      this.addForm.data = new Date();
      this.addForm.purid = this.tmpId;
      const { data: res } = await this.$http.post(
        "supplier/addApply",
        this.addForm
      );

      if (res.success) {
        this.$message.success(res.msg);
      } else {
        this.$message.error(res.msg);
      }
    },

    async showResultDialog(val) {
      const { data: res } = await this.$http.get("getResult?id=" + val.id);
      val.realy_price = res.date[0].reality_price;
      val.actual_product_name = res.date[0].actual_product_name;
      val.supplierId = res.date[0].sid;
      val.result_id = res.date[0].id;
      
      const { data: res1 } = await this.$http.get(
        "getSupplier?id=" + val.supplierId
      );
       this.getApplyList(val.id);
      val.company_name = res1.date[0].company_name; //val.purchasing_id=
      this.clickTarget = val;
      this.showDialogVisible2 = !this.showDialogVisible2;
    },

    async downLoad() {
      window.open(
        "http://localhost:8080/ssm_war_exploded/downloadResultFile/?id=" +
          this.clickTarget.id
      );
    },
    getAcademyList1() {
      return new Promise((resolve, reject) => {
        this.$http.post("academys").then((res) => {
          resolve(res.data);
        });
      });
    },
    async getAcademyList() {
      console.log(1);

      let res;

      //由于promise有等待事件，等同于开启一个新的线程，必须执行完后才能执行下一步
      await BoardJs.getAcademyList().then((resolve) => {
        res = resolve;
      });

      const arry = res.date;
      console.log(res);
      if (res.success) {
        this.AcademysMap = arry;
      } else {
        console.log("academys请求失败！");
      }
    },
    async getCategoriesList() {
      console.log(2);
      const { data: res } = await this.$http.post("categories");
      const arry = res.date;
      if (res.success) {
        this.CategoriesMap = arry;
        console.log(this.CategoriesMap);
      } else {
        console.log("categories请求失败！");
      }
    },
    async getApplyBoard() {
      console.log(2);
      const { data: res } = await this.$http.post("supplier/getApplyBoard");
      const arry = res.date;
      console.log(arry);
      if (res.success) {
        this.applyForBoardList = res.date;
        this.applyForBoardList1 = res.date;
        for (let i = 0; i < this.applyForBoardList.length; i++) {
          this.applyForBoardList[i].startTime = this.$moment(
            this.applyForBoardList[i].startTime
          ).format("YYYY-MM-DD");
          this.applyForBoardList[i].endTime = this.$moment(
            this.applyForBoardList[i].endTime
          ).format("YYYY-MM-DD");
        }
        this.$message.success(res.msg);
      } else {
        console.log("categories请求失败！");
      }
    },
    async getBoardList() {
      console.log("getGoogsList");
      await this.getAcademyList;
      await this.getCategoriesList;
      const { data: res } = await this.$http.post("getBoardList");

      console.log("getGoogsList");
      if (res.success) {
        console.log(res.date.length);
        this.boardListDeal(res);
        this.$message.success(res.msg);
      } else {
        this.$message.error(res.msg);
        console.log("purchasingList请求失败！");
      }
    },
    async boardListDeal(res) {
      let List2 = [];
      this.List = List2;
      const arry = res.date;
      this.count1 = res.date.length;
      console.log("1154");
            for (let i = 0; i < res.date.length; i++) {
        const obj = {};
        obj.Sub = arry[i].product_name;
        for (let j = 0; j < this.AcademysMap.length; j++) {
          if (this.AcademysMap[j].cid == arry[i].cid) {
            obj.academy = this.AcademysMap[j].academy_name;
          }
          console.log("1");
        }
        for (let j = 0; j < this.CategoriesMap.length; j++) {
          if (this.CategoriesMap[j].pid == arry[i].pid) {
            obj.category = this.CategoriesMap[j].product_name;
          }
        }
        if (arry[i].start_time != null)
          obj.start_time = this.$moment(arry[i].start_time).format(
            "YYYY-MM-DD"
          );
        if (arry[i].end_time != null)
          obj.end_time = this.$moment(arry[i].end_time).format("YYYY-MM-DD");
        obj.name = arry[i].purchase_name;
        obj.publish_time = this.$moment(arry[i].publish_time).format(
          "YYYY-MM-DD"
        );
        obj.product_name = arry[i].product_name;
        obj.highest_price = arry[i].highest_price;
        obj.specified = arry[i].specified;
        obj.reason = arry[i].reason;
        obj.site = arry[i].site;
        obj.site_inspection_time = this.$moment(
          arry[i].site_inspection_time
        ).format("YYYY-MM-DD");
        obj.qualifications = arry[i].qualifications;
        obj.notifiedcation = arry[i].notifiedcation;
        obj.errol_contract = arry[i].errol_contract;
        obj.errol_tel = arry[i].errol_tel;
        obj.errol_type = arry[i].errol_type;
        obj.purpose = arry[i].purpose;
        obj.inspection_contract = arry[i].inspection_contract;
        obj.inspection_tel = arry[i].inspection_tel;
        obj.click_count = arry[i].click_count;
        obj.file_place = arry[i].file_place;
        obj.is_result = arry[i].is_result;
        obj.acceess = arry[i].acceess;
        obj.rules = arry[i].rules;
        obj.auction_site = arry[i].auction_site;
        obj.others = arry[i].others;
        obj.id = arry[i].id;
        obj.type = 0;

        this.List.push(obj);
      }
      console.log("1207 ");

      for (let i = 0; i < this.List.length; i++) {
        console.log("dfag");
        if (this.List[i].is_result != null) {
          const obj = {};
          const { data: res } = await this.$http.get(
            "getResult?id=" + this.List[i].id
          );
          if(res.date==null){break;}
          obj.name = "关于" + this.List[i].product_name + "采购的结果公告";
           obj.product_name =  this.List[i].product_name ;
           obj.reason =  this.List[i].reason ;
        obj.highest_price = arry[i].highest_price;  
          obj.type = 1;
          obj.start_time = this.$moment(res.date[0].date).format("YYYY-MM-DD");
          obj.id = res.date[0].pid;
          obj.category = this.List[i].category;
          obj.type = 1;
          this.List.push(obj);
        }
      }
      console.log("1229 ");

      for (let i = 0; i < this.applyForBoardList.length; i++) {
        const obj = {};
        obj.name = this.applyForBoardList[i].boardname;
        obj.start_time = this.applyForBoardList[i].startTime;
        obj.category = "公告";
        this.List.push(obj);
      }

      console.log("kanzheli ");
      this.List1 = this.List;
    },
    handleSizeChange(res) {},
    handleCurrentChange() {},
    async showDialog(row, event, column) {
      if (row.type == 0) {
        this.clickTarget = row;
        this.showDialogVisible = true;
      } else if (row.type == 1) {
        this.showResultDialog(row);
      } else {
        this.$message.success("请注册后进行入围申通");
      }
    },
    test() {
      this.$message.success("test");
    },
    async getSupplier(val) {
      await this.showDialog;
      const { data: res } = await this.$http.get(
        "getSupplier?id=" + val.supplierId
      );

      val.company_name = res.date[0].company_name;
      this.clickTarget = val;
    },
    // 初始页currentPage、初始每页数据数pagesize和数据data
    handleSizeChange: function (size) {
      this.pagesize = size;
      console.log(this.pagesize); //每页下拉显示数据
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage;
      console.log(this.currentPage); //点击第几页
    },
    showDialogClosed() {},
    async search() {
      const { data: res } = await this.$http.post("boardScreen", this.selects);

      this.boardListDeal(res);
    },

    // 点击重置按钮，重置登录表单
    restLoginForm(form) {
      // this.$refs[form].resetFields();
      this.loginForm.name = "";
      this.loginForm.pwd = "";
    },
    // 点击登录按钮，进行登录数据的预验证
    async login(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.loginAction();
        } else {
          this.$message.error("输入的格式不对");
        }
      });
      //})
    },
    async loginAction() {
      if (this.value == 1) {
        const { data: res } = await this.$http.post(
          "administerLogin",
          this.loginForm
        );
        if (!res.success) {
          return this.$message.error("登陆失败!");
        }
        console.log("data");
        console.log("data");
        console.log("data");
        console.log("data");
        console.log(res.date[0]);

        // 将登录成功之后的token,保存到客户端的sessionStorage中
        window.sessionStorage.setItem("token", "1111111");
        window.sessionStorage.setItem("data", JSON.stringify(res.date[0]));
        // 通过编程式导航跳转到后台主页，路由地址是/home
        switch (res.p) {
          case 2:
            this.$router.push("/purchaser");
            break;
          case 3:
            this.$router.push("/president");
            break;
          case 4:
            this.$router.push("/schoolAdmin");
            break;
          case 5:
            this.$router.push("/supper");
            break;
          default:
            return this.$message.error("登陆失败!");
        }
        this.$message.success("登录成功!");
      } else {
        const { data: res1 } = await this.$http.post(
          "supplierLogin",
          this.loginForm
        );
        if (!res1.success) {
          return this.$message.error("登陆失败!");
        }
        // 将登录成功之后的token,保存到客户端的sessionStorage中

        window.sessionStorage.setItem("token", "1111111");
        window.sessionStorage.setItem("data", JSON.stringify(res1.date[0]));
        // 通过编程式导航跳转到后台主页，路由地址是/home
        this.$message.error("来了!");

        switch (res1.p) {
          case 1:
            this.$router.push("/supplier");
            break;
          default:
            return this.$message.error("登陆失败!");
        }
        this.$message.success("登录成功!");
      }
    },
    enRegister() {
      this.$router.push("/register");
    },
  },
};
</script>
<style lang="less" scoped>
.login_container {
  background-color: #7a94ac;
  height: 100%;
}

.login_box {
  width: 600px;
  height: 500px;
  background-color: #647296;
  background-attachment: fixed;
  //border-radius: 3px;
  position: absolute;
  left: 25%;
  top: 50%;
  margin-right: 20px;
  transform: translate(-50%, -50%);
  transition: 0.5s;

  .logo1 {
    background-color: #7a94ac;
    position: absolute;
    width: 100%;
    height: 25%;
    left: 0;
    top: 0;
    img {
      width: 95%;
      height: 100%;
    }
  }
  .avatar_box {
    position: absolute;
    width: 60%;
    height: 75%;
    left: 0;
    top: 25%;
    img {
      width: 100%;
      height: 100%;
    }
  }
}
el-table__body tr:hover {
  .taskName {
    color: rgb(18, 157, 250);
    cursor: pointer;
  }
}
.board_container {
  width: 550px;
  height: 500px;
  position: absolute;
  top: 62%;
  right: -17%;
  transform: translate(-50%, -50%);
  transition: 0.5s;
}

.login_form {
  height: 75%;
  position: absolute;
  left: 60%;
  bottom: 0;
  width: 40%;
  padding: 0 20px;
  box-sizing: border-box;
}
.btns {
  display: flex;
  justify-content: center;
}
.titleName {
  font-size: 40px;
  font-weight: 600;
  color: white;
}
.tit {
  width: 60%;
  margin: 0 auto;
  text-align: center;
  font-size: 24px;
}
</style>
