<template>
  <!-- 交易完成 -->
  <div>
    <!--功能分区 == 页面上的查询/审核-->
    <div class="function-part">
      <!--选择交易类型-->
      <div class="transaction-type-box">
        <div class="transaction-type">商品类别</div>
        <div>
          <template>
            <el-select v-model="selects.categories" placeholder="请选择">
              <el-option
                v-for="item in CategoriesMap"
                :key="item.pid"
                :label="item.product_name"
                :value="item.product_name"
              >
              </el-option>
            </el-select>
          </template>
        </div>
      </div>

      <!--选择物品类型-->
      <div class="product-type-box">
        <div class="product-type">发布来源：</div>
        <div>
          <template>
            <el-select v-model="selects.academy" placeholder="请选择">
              <el-option
                v-for="item in AcademysMap"
                :key="item.cid"
                :value="item.academy_name"
              >
              </el-option>
            </el-select>
          </template>
        </div>
      </div>


      <div class="grade-box">
        <div class="grade">时间选择:</div>
        <div>
          <template>
            <div class="block">
              <el-date-picker
                v-model="selects.value1"
                type="datetimerange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              >
              </el-date-picker>
            </div>
          </template>
        </div>
      </div>

      <!--搜索-->
      <div class="buttons-box">
        <el-button type="primary" @click="search">搜索</el-button>
      </div>
    </div>

    <!--显示相关的审核数据-->
    <div class="main-content">
      <el-table :data="List" style="width: 100%;font-size: 15px;" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="公告名" prop="name"></el-table-column>
        <el-table-column label="发布学院" prop="academy"></el-table-column>
        <el-table-column label="商品类别" prop="category"></el-table-column>
        <el-table-column label="发布时间" prop="start_time"></el-table-column>
        <el-table-column label="截至时间" prop="end_time"></el-table-column>
        <el-table-column label="查看">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showDialog(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
        <el-table-column label="查看结果文件">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showResultDialog(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
        
      </el-table>
      
    <div class="page-part">
      <el-pagination
       @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-size="pagesize"
        :page-sizes="[50]" 
        layout="total,sizes, prev, pager, next"
        :total="List.length" 
      >
      </el-pagination>
    </div>
    </div>

    

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
            ><font face="Calibri">{{ clickTarget.end_time }}</font
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
            ><font face="宋体">{{ clickTarget.academy }}，{{ clickTarget.reason }}，采购预算价,不超过</font
            ><font face="Calibri">{{ clickTarget.highest_price }}</font
            ><font face="宋体"
              >元，拟采购供应商--【{{ clickTarget.company_name }}】--，中标价：</font
            ><font face="Calibri">【 {{ clickTarget.realy_price }} 】</font
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
            ><a
             @click="downLoad"
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
   <el-dialog
      title="采购公告"
      :visible.sync="showDialogVisible"
      width="50%"
      fullscreen
      @close="showDialogClosed"
      class="dialog"
    >
    <div>
        <div class="tit">{{ clickTarget.purchase_name}}</div>
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
              ><br /><span style="font-family: 宋体">
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp;<span style="mso-spacerun: yes">
                  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                  &nbsp; &nbsp; &nbsp; &nbsp;</span
                ><span style="mso-spacerun: yes"> </span
                >2021</span
              ></span
            >
            <span
              style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              "
              >年</span
            ><span style="font-family: 宋体"
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
                >4</span
              ><span
                style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                "
                >月</span
              ><span
                lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "
                >14</span
              ><span
                style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                "
                >日</span
              ><strong style="mso-bidi-font-weight: normal"
                ><span
                  lang="EN-US"
                  style="
                    color: rgb(51, 51, 51);
                    font-family: 'ˎ̥', 'serif';
                    font-size: 20px;
                  "
                >
                </span></strong
            ></span>
          </p>
          <p><span style="font-size: 16px"></span></p>
        </div>
        <div id="div_vote_id"></div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//import boardDetail from './BoardDetail.vue'
//import boardDetail from '/BoardManage.vue'
export default {
  //components:{boardDetail},
  data() {
    return {

      types: [
        { name: "结果公告", id: 2 },
        { name: "招标公告", id: 1 },
      ],
      type_value:'',
      productName: 0,
      currentPage: 0,
      date: "",
      AcademysMap: [],
      CategoriesMap: [],
      List: [],
      resultList: [],
      clickTarget: {},
      count1: 0,
      showDialogVisible: false,
      showDialogVisible1: false,
      showDialogVisible2: false,
      selects: {
        academy: "",
        categories: "",
        value1: [new Date(), new Date()],
        type: '',
      },
      //时间选择器的时间
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",

            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      //设置默认时间
    };
  },
  created() {
    
  },
  mounted() {
    this.getBoardList();
  },
  methods: {
    async getApplyList(id){
 const { data: res } = await this.$http.get(
        "getApplyList?id=" + id
        );
        this.resultList =res.date;
    },
   async showResultDialog(val){
      if(val.is_result==1){
        const { data: res } = await this.$http.get(
        "getResult?id=" + val.id
        );
        this.getApplyList(val.id);
        val.realy_price = res.date[0].reality_price;
         val.actual_product_name = res.date[0].actual_product_name;
         val.supplierId = res.date[0].sid;
         val.result_id = res.date[0].id;
        const { data: res1 } = await this.$http.get(
        "getSupplier?id=" + val.supplierId
      );
      
         val.company_name = res1.date[0].company_name;
         this.clickTarget =val;
         this.showDialogVisible2 =! this.showDialogVisible2;

      }else{
        this.$message.error("还未发布结果名单");
      }

    },

async downLoad(){
     window.open("http://localhost:8080/ssm_war_exploded/downloadResultFile/?id="+this.clickTarget.id);
},

    async getAcademyList() {
      console.log(1);
      const { data: res } = await this.$http.post("academys");
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
    async getBoardList() {
    await  this.getAcademyList();
     await this.getCategoriesList();
      console.log("getGoogsList");
      await this.getAcademyList;
      await this.getCategoriesList;
      const { data: res } = await this.$http.post("getBoardList");
     
      console.log("getGoogsList");
      if (res.success) {
        this.boardListDeal(res);
      } else {
        console.log("purchasingList请求失败！");
      }
    },
    boardListDeal(res){
      let List2=[];
      this.List=List2;
       const arry = res.date;
       this.count1 = res.date.length;
        for (let i = 0; i < res.date.length; i++) {
          const obj = {};
          obj.Sub = arry[i].product_name;
          for (let j = 0; j < this.AcademysMap.length; j++) {
            if (this.AcademysMap[j].cid == arry[i].cid) {
              obj.academy = this.AcademysMap[j].academy_name;
            }
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
          obj.purpose = arry[i].purpose;
          obj.errol_type = arry[i].errol_type;
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
          this.List.push(obj);
        }
    },
    handleSizeChange(res) {
     
    },
    handleCurrentChange() {},
    async showDialog(val) {
      if (val.is_result == 88) {
         const { data: res } = await this.$http.get(
        "getResult?id=" + val.id
      );
         val.realy_price = res.date[0].reality_price;
         val.actual_product_name = res.date[0].actual_product_name;
         val.supplierId = res.date[0].sid;
         val.result_id = res.date[0].id;

          this.getSupplier(val);
        this.showDialogVisible2 = true;
      } else {
        this.clickTarget =val;
        this.showDialogVisible = true;
      }

    },
    async getSupplier(val) {
      await this.showDialog;
         const { data: res } = await this.$http.get(
        "getSupplier?id=" + val.supplierId
      );
        
         val.company_name = res.date[0].company_name;
         this.clickTarget=val;
        console.log("32");
        console.log("32");
        console.log(this.clickTarget);
    },
    showDialogClosed() {
      // this.$refs.addFormRef.resetFields()
    },
    async search() {

// const data=JSON.parse(window.sessionStorage.getItem('data'));
      
//     this.selects.academy="攀枝花学院";
    

      const { data: res } = await this.$http.post(
        "boardScreen",
        this.selects
      );
      
       this.boardListDeal(res);
    },
  },
};
</script>

<style scoped lang='less'>
.main-content {
  margin-top: 3%;
}

.function-part,
.product-name-box,
.product-type-box,
.transaction-type-box,
.grade-box {
  display: flex;
  align-items: center;
}

.product-name,
.product-type,
.transaction-type,
.grade {
  font-size: 14px;
  width: 100px;
}
.dialog > .el-dialog {
  overflow-y: auto !important ;
  height: 500px !important;
}
.tit {
  width: 60%;
  margin: 0 auto;
  text-align: center;
  font-size: 24px;
}
.myclose {
  top: 18px;
  right: 36.6px;
  font-size: 16px;
  position: fixed;
  cursor: pointer;
}
.close:hover {
  color: blue;
}
.info {
  width: 50%;
  height: 40px;
  margin: 0 auto;
  text-align: center;
  line-height: 40px;
  border: solid 1px #dadada;
  color: #666;
  background-color: #f6f5f5;
}
.content p {
  width: 70%;
  margin: 10px auto;
  text-indent: 2em;
  line-height: 37px;
}
</style>