package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnkj on 2018/1/25.
 * 双随机中间ben
 */
public class DoubleRandomCenterBean implements Serializable{
    public List<DateBean> dataBeans = new ArrayList<>();

    public List<DateBean> getDataBeans() {
        return dataBeans;
    }

    public void setDataBeans(List<DateBean> dataBeans) {
        this.dataBeans = dataBeans;
    }

    public static class DateBean implements Serializable {
        private String title;//检查内容标题     检查内容标题:   一、屠宰资质
        private List<CheckItem> checkItems=new ArrayList<>();//检查内容

        public String getTitle() {
            return title;
        }

        public DateBean setTitle(String title) {
            this.title = title;
            return this;
        }

        public List<CheckItem> getCheckItems() {
            return checkItems;
        }

        public void setCheckItems(List<CheckItem> checkItems) {
            this.checkItems = checkItems;
        }

        public DateBean setCheckItems(CheckItem checkItems) {
            this.checkItems.add(checkItems);
            return this;
        }

        public static class CheckItem  implements Serializable {
            private String checkContent;//检查内容 检查内容: 1.生猪定点屠宰证书和标志牌

            private List<CheckItemData1> checkData1s=new ArrayList<>();

            public List<CheckItemData1> getCheckData1s() {
                return checkData1s;
            }

            public CheckItem setCheckData1s(List<CheckItemData1> checkData1s) {
                this.checkData1s = checkData1s;
                return this;
            }

            public CheckItem setCheckData1s(CheckItemData1 checkData1s) {
                this.checkData1s.add(checkData1s);
                return this;
            }

            public String getCheckContent() {
                return checkContent;
            }

            public CheckItem setCheckContent(String checkContent) {
                this.checkContent = checkContent;
                return this;
            }

                public static class CheckItemData1  implements Serializable{
                private String checkBase;//检查依据 检查依据：《生猪屠宰管理条例》第六条、第七条

                private List<CheckItemData2> checkItemData2s=new ArrayList<>();

                public String getCheckBase() {
                    return checkBase;
                }

                public CheckItemData1 setCheckBase(String checkBase) {
                    this.checkBase = checkBase;
                    return this;
                }

                public List<CheckItemData2> getCheckItemData2s() {
                    return checkItemData2s;
                }

                public CheckItemData1 setCheckItemData2s(List<CheckItemData2> checkItemData2s) {
                    this.checkItemData2s = checkItemData2s;
                    return this;
                }
                public CheckItemData1 setCheckItemData2s(CheckItemData2 checkItemData2s) {
                    this.checkItemData2s.add(checkItemData2s);
                    return this;
                }

                public static class CheckItemData2  implements Serializable {
                    private String checkRequire;//检查要求 检查要求:  生猪定点屠宰证书上的企业名称、经营范围、法定代表人、经营地点与营业执照相符。
                    private String checkResult = "是";//检查结果    检查结果： 相符□ 不符□ // 是□ 否□
                    private String checkRemark = "";//检查备注

                    public String getCheckRequire() {
                        return checkRequire;
                    }

                    public CheckItemData2 setCheckRequire(String checkRequire) {
                        this.checkRequire = checkRequire;
                        return this;
                    }

                    public String getCheckResult() {
                        return checkResult;
                    }

                    public CheckItemData2 setCheckResult(String checkResult) {
                        this.checkResult = checkResult;
                        return this;
                    }

                    public String getCheckRemark() {
                        return checkRemark;
                    }

                    public CheckItemData2 setCheckRemark(String checkRemark) {
                        this.checkRemark = checkRemark;
                        return this;
                    }

                }
            }


        }

    }

    public static DoubleRandomCenterBean getInstance() {
        DoubleRandomCenterBean bean = new DoubleRandomCenterBean();
        List<DateBean> beanList =new ArrayList<>();
        {
            /**
             检查内容标题:   一、屠宰资质
             检查内容: 1.生猪定点屠宰证书和标志牌
             检查依据：《生猪屠宰管理条例》第六条、第七条
             检查要求:  生猪定点屠宰证书上的企业名称、经营范围、法定代表人、经营地点与营业执照相符。
             检查结果： 相符□ 不符□ // 是□ 否□
             备注
             包含关系 标题-》检查内容-》检查依据-》检查项
             **/
            DateBean dataBean = new DateBean();
            dataBean.setTitle("一、屠宰资质")
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("1.生猪定点屠宰证书和标志牌")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰管理条例》第六条、第七条")
                            .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                    .setCheckRequire("生猪定点屠宰证书上的企业名称、经营范围、法定代表人、经营地点与营业执照相符"))
                            ));
            beanList.add(dataBean);
        }

        {
            DateBean dataBean = new DateBean();
            dataBean.setTitle("二、布局及硬件设施设备")
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("2.布局")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《猪屠宰与分割车间设规范》（GB50317-2009）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("厂区生产区和非生产区分隔明显"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("生产区清洁区与非清洁区分隔合理"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("生产区设置生猪与废弃物出入口"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("生产区设置人员和生猪产品出入口"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("设置生猪产品与生猪、废弃物通道"))
                            ))
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("3.宰前设施设备")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《猪屠宰与分割车间设规范》（GB50317-2009）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("待宰间面积能满足生产需要。(待宰圈面积≥设计屠宰量/h×7×0.6)"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("待宰区设有生猪冲淋设施"))
                            )
                    )
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("4.屠宰设施设备")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《猪屠宰与分割车间设计规范》（GB50317-2009）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("屠宰车间地面防滑、易清洁、耐腐蚀，墙面整洁易清洁"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("生产车间不积水，排污沟易清洁"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("配备致昏、喷淋、浸烫脱毛、劈半、副产品转运等相应的设施设备"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《猪屠宰与分割车间设计规范》（GB50317-2009）、《生猪屠宰产品品质检验规程》（GB/T 17996-1999）、《生猪屠宰检疫规范》（NY/T 909-2004）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("车间设有同步检验检疫操作台，并设有标示牌"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("车间内生产线旁设有检验检疫室，配有显微镜等相应的检验设备"))
                            )
                    )
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("5.信息化设施")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《关于加强全省动物卫生监督远程视频监控系统运行管理工作的通知》（苏农办牧〔2016〕23号）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("视频监控设备能正常使用"))
                            )
                    )
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("6.消毒设施设备")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《动物防疫条件审查办法》第十二条")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("生猪进场入口处应设有车轮消毒池"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("入场动物卸载区域有固定的车辆消毒场地，并配有车辆清洗、消毒设备"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("屠宰间出入口设置人员更衣消毒室，且正常使用"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《猪屠宰与分割车间设计规范》（GB50317-2009）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("各检验检疫操作区设有刀具消毒设施"))
                            )
                    );

            beanList.add(dataBean);
        }


        {
            DateBean dataBean = new DateBean();
            dataBean.setTitle("三、人员要求")
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("7.品质检验和屠宰技术人员")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰管理条例实施办法》第十八条")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("肉品品质检验人员经考核合格，人数能满足生产需要"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《食品安全法》第三十四条、《生猪屠宰管理条例实施办法》第七条第三项")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("肉品品质检验人员和屠宰技术人员持有依法取得的健康证明"))
                            )
                    );

            beanList.add(dataBean);
        }

        {
            DateBean dataBean = new DateBean();
            dataBean.setTitle("四、质量管理")
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("8.进场查验*")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰管理条例实施办法》第十一条")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("查验《动物检疫合格证明》，有记录并保存检疫证"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰产品品质检规程》（GB/T17996-1999）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("对进场生猪进行临床健康检查，有记录"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰管理条例实施办法》第十一条、《动物检疫管理办法》、第二十二条")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("查验畜禽标识佩戴情况，有记录"))
                            )
                    )
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("9.待宰*")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰产品品质检规程》（GB/T17996-1999）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("根据生猪不同来源按要求分圈编号"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰管理条例实施办法》《生猪屠宰产品品质检验规程》（GB/T17996-1999）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("如实记录待宰生猪数量、临床健康检查情况、隔离观察情况、停食静养情况，以及货主等信息"))
                            )
                    )
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("10.肉品品质检验*")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰管理条例实施办法》第十四条、《生猪屠宰产品品质检验规程》（GB/T17996-1999）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("按照检验规程对头、体表、内脏、胴体进行检验"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰管理条例实施办法》第十七条、《生猪屠宰产品品质检验规程》（GB/T17996-1999）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("对胴体检查，摘除肾上腺、甲状腺、病变淋巴结，对检验不合格的生猪产品进行修割"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《农业部关于加强生猪定点屠宰环节“瘦肉精”监管工作的通知》")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("对待宰生猪或者在屠宰过程中进行“瘦肉精”等检验"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰管理条例实施办法》第十六条")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("是否对检验合格的生猪产品出具《肉品品质检验合格证》，在胴体上加盖检验合格印章"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰产品品质检验规程》（GB/T17996-1999）、《关于开展2017年度养殖运输屠宰环节“瘦肉精”监测工作的通知》（苏农办牧〔2017〕14号）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("按照5%比例开展“瘦肉精”监测，如实完整记录肉品品质检验、“瘦肉精”等检验结果"))
                            )
                    )
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("11.无害化处理*")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪定点屠宰厂（场）病害猪无害化处理管理办法》第三条、《生猪屠宰管理条例实施办法》第二十条")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("对待宰死亡生猪、检验检疫不合格生猪或者生猪产品，以及召回生猪产品进行无害化处理"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《病死及病害动物无害化处理技术规范》")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("采用密闭容器运输病害生猪或生猪产品"))
                            )
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《关于进一步规范生猪屠宰环节病害猪无害化处理监督管理工作的通知》（苏农牧〔2016〕7号）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("使用全省统一制式的《病害猪及病害猪产品无害化处理记录簿》、《屠宰环节病害猪及病害猪产品确认登记簿》（分驻场官方兽医用和品质检验员用）、《病害猪及病害猪产品无害化处理交接簿》记录表格；各类表格记录的内容齐全，肉品检验人员、驻场官方兽医、货主、无害化处理人员、屠宰企业负责人等相关责任人签名齐全"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("无害化处理过程视频资料齐全，与无害化处理记录表登记的批次相符（抽查1-2个批次）"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("委托无害化中心处理的签订有委托协议"))
                            )
                    );

            beanList.add(dataBean);

        }

        {
            DateBean dataBean = new DateBean();
            dataBean.setTitle("五、档案管理")
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("12.生产质量管理档案*")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《生猪屠宰管理条例》《生猪屠宰管理条例实施办法》《动物防疫条件审查办法》《生猪定点屠宰厂（场）病害猪无害化处理管理办法》《生猪屠宰产品品质检验规程》（GB/T17996-1999）、《农业部 食品药品监管总局关于进一步加强畜禽屠宰检验检疫和畜禽产品进入市场或者生产加工企业后监管工作的意见》")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("将进场查证验物登记记录、分圈编号记录、待宰记录、肉品品质检验记录、“瘦肉精”等检验记录、无害化处理记录、消毒记录、生猪来源和产品流向记录、设施设备检验检测保养记录等归档"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("各种记录档案保存两年以上"))
                            )
                    );

            beanList.add(dataBean);
        }

        {
            DateBean dataBean = new DateBean();
            dataBean.setTitle("六、安全生产管理")
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("13.明确安全生产责任")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《农业部关于指导做好畜禽屠宰行业安全生产工作的通知》（农医发﹝2016﹞4号）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("屠宰企业建立以企业法定代表人为安全生产总负责人、覆盖屠宰生产全过程、涵盖企业全体职工和所有岗位的安全生产责任体系"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("明确岗位安全生产职责，细化岗位安全生产操作程序"))
                            )
                    )
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("14.严格执行安全生产审批备案制度")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《农业部关于指导做好畜禽屠宰行业安全生产工作的通知》（农医发﹝2016﹞4号）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("屠宰车间建筑消防要符合《消防法》的规定，经公安消防部门验收合格"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("屠宰企业的锅炉、高压容器和液氨存贮设备，要符合《特种设备安全法》、《危险化学品安全管理条例》的规定，取得特种设备、危险化学品存储设备使用许可"))
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("对从事特种设备操作的人员，经培训合格取得相应资质后，方能上岗"))
                            )
                    )
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("15.畜禽屠宰企业要建立屠宰设施设备检修保养制度")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《农业部关于指导做好畜禽屠宰行业安全生产工作的通知》（农医发﹝2016﹞4号）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("明确专人，对屠宰设施设备和屠宰关键环节，进行定期巡视检查，及时发现隐患，及时排除"))
                            )
                    )
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("16.建立健全畜禽屠宰生产应急预案")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《农业部关于指导做好畜禽屠宰行业安全生产工作的通知》（农医发﹝2016﹞4号）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("通过培训教育、应急演练等方式，确保企业人员熟悉所在岗位危险因素、危害程度、预防措施以及应急处置方法等相关知识"))
                            )
                    )
                    .setCheckItems(new DateBean.CheckItem().setCheckContent("17.健全畜禽屠宰安全和平档案记录制度")
                            .setCheckData1s(new DateBean.CheckItem.CheckItemData1().setCheckBase("《农业部关于指导做好畜禽屠宰行业安全生产工作的通知》（农医发﹝2016﹞4号）")
                                    .setCheckItemData2s(new DateBean.CheckItem.CheckItemData1.CheckItemData2()
                                            .setCheckRequire("畜禽屠宰企业要如实记录屠宰设施设备检修维护、企业从业人员安全生产培训、安全隐患排查和整改措施落实等情况，并及时归档留存"))
                            )
                    );


            beanList.add(dataBean);
        }
        bean.setDataBeans(beanList);
        return bean;
    }
}
