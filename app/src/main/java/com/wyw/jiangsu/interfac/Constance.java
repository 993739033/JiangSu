package com.wyw.jiangsu.interfac;

import static com.wyw.jiangsu.runnable.NetClient.BASE_DOMAIN;

/**
 * Created by wyw on 2016/12/22.
 */
public interface Constance {

    //机器码和序列号
    String DATA_MACHINE_NUMBER = "MachineNumber";
    String DATA_SERIAL_NUMBER = "SerialNumber";
    String DATA_PERMISSION = "Permission";

    String USER_OBJECT = "user_object";
    String USER_NAME = "user_name";
    String USER_PSW = "user_psw";

    //下载
    int TYPE_APK_DONE = 0; //apk下载完成标志
    int TYPE_DB_DONE = 1; //db下载完成标志

    //动物种类 产品种类
    String TYPE_PRINT_ANIM_A = "AMA";
    String TYPE_PRINT_ANIM_B = "AMB";
    String TYPE_PRINT_PRODUCT_A = "PDA";
    String TYPE_PRINT_PRODUCT_B = "PDB";
    String TYPE_PRINT_JIANYI_CHULI = "JIANYICHULI";
    String TYPE_PRINT_QUERY_JIANI_CHULI = "QUERYJIANYICHLI";
    String TYPE_PRINT_ANIM_APPLY_DETIL = "ANIM_APPLY_DETIL";
    String TYPE_PRINT_APPLY_DETIL = "APPLY_DETTL";
    String TYPE_PRINT_AREA = "area";
    String TYPE_PRINT_RUZHONG = "ruzhong";
    String TYPE_PRINT_ZHONGDAN = "zhongdan";
    String TYPE_PRINT_RZ_ONE = "1";
    String TYPE_PRINT_RZ_TWO = "2";
    String TYPE_PRINT_RZ_THREE = "3";
    String TYPE_PRINT_RZ_FOUR = "4";
    String TYPE_PRINT_RZ_FIVE = "5";
    String TYPE_PRINT_RZ_SIX = "6";
    String TYPE_PRINT_RZ_SEVEN = "7";
    String TYPE_PRINT_RZ_EIGHT = "8";
    String TYPE_PRINT_RZ_NINE = "9";
    String TYPE_PRINT_RZ_TEN = "10";
    String TYPE_PRINT_QUERY_APPLY_DETIL = "TYPE_PRINT_QUERY_APPLY_DETIL";


    String ACTIVITY_DATA = "data";
    String ACTIVITY_TYPE = "type";
    String IMAGE_PATH = BASE_DOMAIN + "JiangSuFileWebService/SBImages/";//本地测试
    String IMAGE_PATHH = BASE_DOMAIN + "JiangSuFileWebservice/UploadFiles/";//产地检疫服务器
    String PRINT_URL_ANIA = BASE_DOMAIN + "PT/PhoneE/AQASQR.aspx?N=";//四证打印二维码前缀动物A
    String PRINT_URL_ANIB = BASE_DOMAIN + "PT/PhoneE/AQBSQR.aspx?N=";//四证打印二维码前缀动物B
    String PRINT_URL_PROA = BASE_DOMAIN + "PT/PhoneE/APASQR.aspx?N=";//四证打印二维码前缀产品A
    String PRINT_URL_PROB = BASE_DOMAIN + "PT/PhoneE/APBSQR.aspx?N=";//四证打印二维码前缀产品B
    int ACTIVITY_REQUEST_CODE = 100;
    int ACTIVITY_CODE = 200;

    String SP_DB_VERSION = "db_version";
    String START_ACTIVITY_TYPE = "type";
    String START_ACTIVITY_DATA = "data";
    String START_ACTIVITY_PRINTID = "print_id";

    //问答的序列
    String THIRD_DATALIST = "third_datalist";

    String Item1_title = "规模养殖场日常监督检查记录";
    String Item2_title = "生猪屠宰厂(场)日常监督检查记录表";
    String Item3_title = "动物诊疗场所监督检查记录";
    String Item4_title = "无害化处理场所监管记录";
    String Item5_title = "隔离场所监督检查记录";
    String Item6_title = "饲料生产企业日常检查";
    String Item7_title = "兽药生产企业日常检查";
    String Item8_title = "兽药经营企业日常检查";
    String Item9_title = "生鲜乳收购站检查表";


    String Item1_1 = "1.有有效的防疫条件合格证及年度报告记录。";
    String Item1_2 = "2.养殖场的名称、法定代表人、地址、经营范围与动物防疫条件合格证标注一致。";
    String Item1_3 = "3.具备县级以上畜牧兽医行政主管部门备案登记证明。";
    String Item1_4 = "4.与主要场所距离符合动物防疫条件要求。";
    String Item1_5 = "5.分区与布局合理，功能区齐全，净道、污道分开，符合动物防疫条件要求。";
    String Item1_6 = "6.具备与养殖规模相适应的消毒、免疫、兽药、饲料和疫苗存储、诊疗、隔离、无害化处理、防鼠防鸟防虫（种畜禽场）等设施设备，并处于有效运行状态。";
    String Item1_7 = "7.动物饲养场、养殖小区有与其养殖规模相适应的执业兽医或乡村兽医，从业人员健康状况符合规定。";
    String Item1_8 = "8.养殖场定期对生产、技术人员开展免疫、消毒等技术培训。";
    String Item1_9 = "9.制定免疫、用药、检疫申报、疫情报告、消毒、无害化处理、疫病净化（乳畜、种畜禽场）、畜禽标识等制度并上墙。";
    String Item1_10 = "10.有合理的免疫程序，按照规定开展强制免疫，按照规定要求佩戴免疫标识。";
    String Item1_11 = "11.按规定对人员、环境、棚舍、动物、运载工具、用具、工作服、等进行消毒。";
    String Item1_12 = "12.按照规定对疑似染疫动物实施隔离、诊断、治疗。";
    String Item1_13 = "13.按照规定对病死或者死因不明的动物尸体进行无害化处理。";
    String Item1_14 = "14.跨省引进乳用种用动物经过审批，按照规定进行隔离观察。";
    String Item1_15 = "15.动物出栏按规申报检疫并依法取得检疫证明。";
    String Item1_16 = "16.采购兽药有合法的产品批准文号,保存购进凭证，可追溯。";
    String Item1_17 = "17.购进兽药按相应品种的贮存条件贮藏和保管。";
    String Item1_18 = "18.治疗和预防用药符合兽药使用规范，不得超范围、超剂量使用，不得使用假劣兽药、过期兽药、原料药、人用药及国家明令禁止的兽药及其他化合物等，有执业兽医或乡村兽医开具的处方。";
    String Item1_19 = "19.严格执行停药期相关规定，停药期内不得屠宰或上市销售。";
    String Item1_20 = "20.采购饲料查验生产许可证，采购添加剂预混料和饲料添加剂查验批准文号，购进饲料按相应品种的贮存条件贮藏和保管。";
    String Item1_21 = "21.不得在饲料、动物饮水中添加禁用物质或对人体有害物质，不在反刍动物饲料中添加乳和乳制品以外的动物源性成分。";
    String Item1_22 = "22.有生产记录档案，详细记录存栏、生产、调入、调出、死淘等情况，记录保存2年以上。";
    String Item1_23 = "23.有防疫档案，详细记录消毒、免疫、抗体监测情况，记录保存2年以上。";
    String Item1_24 = "24.有投入品管理档案，详细记录兽药及饲料的采购、使用、休药等情况，记录保存2年以上。";
    String Item1_25 = "25.有病死畜禽无害化处理记录，记录保存2年以上。";
    String Item1_26 = "26.有员工培训计划和培训记录。";

    String Item2_1 = "1.屠宰设施设备能否正常运行。 ";
    String Item2_2 = "2.无害化处理设施设备能否正常运转。";
    String Item2_3 = "3.是否查验《动物检疫合格证明》。 ";
    String Item2_4 = "4.是否对进场生猪进行临床健康检查。 ";
    String Item2_5 = "5.是否查验畜禽标识佩戴情况。";
    String Item2_6 = "6.是否按要求分圈编号。 ";
    String Item2_7 = "7.是否及时对生猪体表进行清洁 ";
    String Item2_8 = "8.是否达到宰前停食静养的要求。 ";
    String Item2_9 = "9.对临床健康检查状况异常生猪是否进行隔离观察或者按检验规程急宰。 ";
    String Item2_10 = "10.是否按规定进行检疫申报。";
    String Item2_11 = "11.是否如实记录待宰生猪数量、临床健康检查情况、隔离观察情况、停食静养情况，以及货主等信息。";
    String Item2_12 = "12.是否按照屠宰工艺流程进行屠宰操作。 ";
    String Item2_13 = "13.是否按照检验规程进行肉品品质检验。 ";
    String Item2_14 = "14.是否摘除肾上腺、甲状腺、病变淋巴结，是否对检验不合格的生猪产品进行修割。";
    String Item2_15 = "15.是否对待宰生猪或者在屠宰过程中进行“瘦肉精”等检验。";
    String Item2_16 = "16.是否对检验合格的生猪产品出具《肉品品质检验合格证》，在胴体上加盖检验合格印章。";
    String Item2_17 = "17.是否对屠宰车间、屠宰设备、器械及时清洗、消毒。 ";
    String Item2_18 = "18.是否如实完整记录肉品品质检验、“瘦肉精”等检验结果。 ";
    String Item2_19 = "19.是否对待宰死亡生猪、检验检疫不合格生猪或者生猪产品进行无害化处理。 ";
    String Item2_20 = "20.是否如实记录无害化处理病害生猪或者生猪产品数量、处理时间、处理人员等。 ";
    String Item2_21 = "21.出场肉类是否附有《肉品品质检验合格证》和《动物检疫合格证明》。";
    String Item2_22 = "22.胴体外表面是否加盖检验合格章、动物检疫验讫印章，经包装生猪产品是否附具检验合格标志、加施检疫标志。 ";
    String Item2_23 = "23.是否如实记录出场生猪产品规格、数量、肉品品质检验证号、动物检疫证明号、屠宰日期、销售日期以及购货者名称、地址、联系方式等信息。 ";
    String Item2_24 = "24.肉品品质检验人员是否经考核合格。";
    String Item2_25 = "25.肉品品质检验人员和屠宰技术人员是否持有依法取得的健康证明。";
    String Item2_26 = "26.屠宰设施设备能否正常运行。 ";
    String Item2_27 = "27.是否按照国家《生猪等畜禽屠宰统计报表制度》的要求，及时报送屠宰相关信息。 ";
    String Item2_28 = "28.是否按要求报告安全生产信息。 ";
    String Item2_29 = "29.是否将进场查证验物登记、分圈编号、待宰、品质检验、“瘦肉精”等检验记录、无害化处理、消毒、生猪来源和产品流向、设施设备检验检测保养记录等归档。";

    String Item3_1 = "1.有有效《动物诊疗许可证》，并悬挂于诊疗场所显著位置。";
    String Item3_2 = "2.诊疗机构的名称、法定代表人（负责人）、从业地点、诊疗活动范围与动物诊疗许可证标注一致。";
    String Item3_3 = "3.按规定配备执业兽医师，执业兽医师按照规定注册或备案。";
    String Item3_4 = "4.兽医人员和服务人员持有健康证明材料，相关资格证书与健康证明悬挂于诊疗场所的明显位置。";
    String Item3_5 = "5.诊疗场所面积符合规定要求，与主要场所距离符合相关防疫规定。";
    String Item3_6 = "6.场所设有独立出入口，具有布局合理的诊疗室、手术室、药房等功能区，兼营区域与诊疗区域分别设置。";
    String Item3_7 = "7.具有诊断、手术、消毒、冷藏、常规化验、污水处理等器械设备，并处于有效运行状态。";
    String Item3_8 = "8.建立诊疗服务、疫情报告、卫生消毒、兽药处方及药物管理、病害组织及医疗废弃物无害化处理等制度，相关制度规范齐全并上墙。";
    String Item3_9 = "9.按照规定使用病历、开具处方，使用规范的病历、处方笺。";
    String Item3_10 = "10.建立病历档案，记录真实，病历保存3年以上。";
    String Item3_11 = "11.按规采购、保存、使用兽药；建立兽药采购、兽药使用档案，记录真实、规范。";
    String Item3_12 = "12.按规处理病死动物、动物病理组织、医疗废水和废弃诊弃物；规范建立医疗废弃物和病死动物、动物病理组织处理记录，记录真实、规范。";
    String Item3_13 = "13.按规进行卫生消毒；规范建立卫生消毒记录，记录真实、规范。";
    String Item4_1 = "1.有有效的防疫条件合格证及年度报告记录。";
    String Item4_2 = "2.无害化处理场的名称、法定代表人、地址、经营范围与动物防疫条件合格证标注一致。";
    String Item4_3 = "3.与主要场所距离符合动物防疫条件要求。";
    String Item4_4 = "4.分区与布局合理，功能区齐全，符合动物防疫条件要求。";
    String Item4_5 = "5.具备有与无害化处理规模相适应的消毒、无害化处理、污水污物处理、电子监控等设施设备，并处于有效运行状态。";
    String Item4_6 = "6.运输车辆符合密闭、防水、耐腐蚀、易消毒等要求，配备GPS定位和视频监控系统，并处于有效运行状态。";
    String Item4_7 = "7.高温高压等设备经过计量检验并具备合格证明。";
    String Item4_8 = "8.配备与其无害化处理规模相适应的专业技术人员，从事无害化处理的工作人员健康情况符合规定，按照规定落实人员防护措施。";
    String Item4_9 = "9.对收集运输、无害化处理生产等工作人员进行相关培训，培训记录真实、规范。";
    String Item4_10 = "10.建立病害动物和动物产品入场登记、消毒、处理、无害化处理后的物品流向登记、人员防护等制度，相关制度齐全规范并上墙。";
    String Item4_11 = "11.规范建立病死畜禽及畜禽产品入场登记记录。";
    String Item4_12 = "12.按照规定对病死畜禽及畜禽产品进行存放，规范建立进出库台账。";
    String Item4_13 = "13.按照规定对病死畜禽及畜禽产品进行无害化处理，无害化处理生产记录真实、规范。";
    String Item4_14 = "14.按照规定对无害化处理车间、冷库、运载工具、相关场所环境等进行消毒，消毒记录真实、规范。";
    String Item4_15 = "15.规范建立病死畜禽及畜禽产品无害化处理后的物品流向登记记录，销售对象具备相应资质。";
    String Item5_1 = "1.有有效的《动物防疫条件合格证》及年度报告记录。";
    String Item5_2 = "2.隔离场的名称、法定代表人、地址、经营范围与动物防疫条件合格证标注一致。";
    String Item5_3 = "3.与主要场所距离符合防疫条件要求。";
    String Item5_4 = "4.分区与布局合理，功能区齐全，净道、污道分开，符合动物防疫条件要求。";
    String Item5_5 = "5.配备与隔离规模相适应的消毒、投入品存储、隔离、诊疗、监测、无害化处理、污水污物处理等设施设备，并处于有效运行状态。";
    String Item5_6 = "6.建立动物及动物产品进出登记、免疫、用药、消毒、疫情报告、无害化处理等制度并上墙。";
    String Item5_7 = "7.配备与其隔离规模相适应的执业兽医，从业人员健康状况符合规定。";
    String Item5_8 = "8.隔离场定期对技术、饲养人员开展免疫、消毒等技术培训。";
    String Item5_9 = "9.按照规定对人员、环境、圈舍、动物、运载工具、用具、工作服等进行消毒。";
    String Item5_10 = "10.隔离期间，对动物进行免疫、治疗时，需经出入境检验检疫部门批准，不得自行处置。";
    String Item5_11 = "11.隔离期间，不得随意转移隔离检疫动物和私自采集、保存运送检疫动物血液、组织、精液、分泌物等样品或者病料。";
    String Item5_12 = "12.隔离期满，无动物疫病，经出入境检验检疫部门许可后出场。";
    String Item5_13 = "13.按照国家有关规定对病死动物进行无害化处理。";
    String Item5_14 = "14.有防疫档案，详细记录消毒、隔离观察、监测等情况，记录保存2年以上。";
    String Item5_15 = "15.有病死畜禽无害化处理记录，记录保存2年以上。";
    String Item5_16 = "16.有员工培训计划和培训记录。";
    String Item6_1 = "1.具有与生产品种对应的有效的生产许可证、产品批准文号。";
    String Item6_2 = "2.用电、高温、高压、传动、提升、下料坑、吊物孔、维修操作平台、爬梯等有警示标识和安全防护设施。危险化学品、易燃易爆物品存放区有警示标识。";
    String Item6_3 = "3.原料分类堆放、整齐，做到“一垛一卡”，原料来源可靠，标签符合要求。";
    String Item6_4 = "4.生产设备符合生产产品的要求，齐全、完好，定期维护。有设备维护保养记录。";
    String Item6_5 = "5.成品分类堆放、整齐，成品建立垛位标识卡，成品标签符合要求。";
    String Item6_6 = "6.检验仪器满足生产产品检验需要，齐全、完好。";
    String Item6_7 = "7.化验室正常开展检验工作。";
    String Item6_8 = "8.建立《饲料质量安全管理规范》规定的制度、规程和记录。";
    String Item6_9 = "9.原料供应商档案收集齐全，包括营业执照、生产许可证、产品批准文号、审查合格证复印件、联系电话等。";
    String Item6_10 = "10.饲料原料采购符合要求。";
    String Item6_11 = "11.应逐批对采购的原料进行检验或者查验。";
    String Item6_12 = "12.每3个月选择5种原料，自行或委托有资质的机构对其卫生指标进行检测。";
    String Item6_13 = "13.饲料药物添加剂使用符合要求，有进货台账和使用记录。";
    String Item6_14 = "14.原料领取、配料、生产过程各岗位记录齐全、规范。";
    String Item6_15 = "15.产品标签内容规范。标签应当专库（柜）存放，摆放整齐，专人管理。标签领用记录规范。";
    String Item6_16 = "16.依据产品质量标准中规定的出厂检验项目及检验方法对产品进行出厂检验。有产品出厂检验记录。";
    String Item6_17 = "17.每周抽查生产的5个批次产品，按照产品质量标准中规定的检验方法对产品进行主成分指标自行检验。" +
            "维生素预混合饲料：两种以上维生素；" +
            "微量元素预混合饲料：两种以上微量元素；" +
            "复合预混合饲料：两种以上维生素和两种以上微量元素；" +
            "浓缩饲料、配合饲料、精料补充料：粗蛋白质、粗灰分、钙、总磷。";
    String Item6_18 = "18.每批次产品有留样观察记录。";
    String Item6_19 = "19.产品销售台账应当包括产品的名称、数量、生产日期、生产批次、质量检验信息、购货者名称及其联系方式、销售日期等信息。";

    String Item7_1 = "1.持有兽药生产许可证和兽药GMP证书，且兽药生产许可证和兽药GMP证书在有效期内。";
    String Item7_2 = "2.所生产的产品批准文号应在有效期内。";
    String Item7_3 = "3.有至少2名具有高中（含中专）以上文化程度检验人员，并持有省级以上兽药监察机构核发的培训合格证或上岗证书。";
    String Item7_4 = "4.生产设备应能满足生产产品的要求。";
    String Item7_5 = "5.检验仪器应能满足生产产品检验的需求。";
    String Item7_6 = "6.计量仪器和压力设备应经计量检定并在有效期内。";
    String Item7_7 = "7.生产设备和检验仪器应有使用记录。";
    String Item7_8 = "8.购进的原料应符合相应产品的要求（不得使用工业级、口服级原料生产注射剂产品），检查原料药是否有批准文号，并建立购进记录。";
    String Item7_9 = "9.兽药产品是否在GMP车间生产。";
    String Item7_10 = "10.GMP车间是否生产其他工业产品（如非药品）。";
    String Item7_11 = "11.应建立批生产记录，内容应完整、规范、真实。";
    String Item7_12 = "12.应对进厂原料、出厂产品开展检验，记录应完整、规范、真实。";
    String Item7_13 = "13.生产、环境卫生应符合要求。";
    String Item7_14 = "14.危险化学品、易制毒品的存放应有专库或专柜存放，并有防火防盗措施。";

    String Item8_1 = "1.持有兽药经营许可证并在有效期内。";
    String Item8_2 = "2.经营的产品是否超出兽药经营许可证的经营范围(如兽用生物制品)。";
    String Item8_3 = "3.质量负责人（或质量机构负责人）是否经培训合格取得上岗证并在岗。";
    String Item8_4 = "4.兽用生物制品经营企业从事质量管理的人员应不少于2人。";
    String Item8_5 = "5.经营地点应当与《兽药经营许可证》载明的地点一致。";
    String Item8_6 = "6.仓库里产品是否分区、分类堆放，建立货位卡，仓库不得改变用途，阴凉库应符合要求。";
    String Item8_7 = "7.不得经营禁用药物和已规定停止使用的兽药，不得经营人用药品，原料药销售应符合规定。";
    String Item8_8 = "8.经营的兽药应有合法的兽药批准文号，并在有效期内。";
    String Item8_9 = "9.兽药产品标签应与农业部批准的样本一致。";
    String Item8_10 = "10.经营的兽药产品应当有二维码标识。";
    String Item8_11 = "11.兽用处方药应分区陈列，并有相应标识，不得开架自选。";
    String Item8_12 = "12.库房内兽用处方药应分区存放。";
    String Item8_13 = "13.购进的兽药应有来源证明（供应商审计档案、供货协议、购货票据等）。";
    String Item8_14 = "14.销售处方药应保存兽医处方。";
    String Item8_15 = "15.应建立产品销售记录，内容齐全。";
    String Item8_16 = "16.销售处方药应单独建立销售记录。";
    String Item8_17 = "17.应开展进货扫码入库和出货扫码出库。";
    String Item8_18 = "18.经营兽用生物制品应有运输保温设施设备，并有详细跟踪记录。";

    String Item9_1 = "1.有固定的站（厅、场所）址";
    String Item9_2 = "2.有功能区划分，设有挤贮奶厅、待挤区、设备室、化验室、办公室等区域";
    String Item9_3 = "3.布局合理，挤贮奶厅与办公区、生活区分开";
    String Item9_4 = "4.具有动物卫生防疫合格证（正、副本）";
    String Item9_5 = "5.卫生状况总体良好";
    String Item9_6 = "6.站内环境整洁、无异味";
    String Item9_7 = "7.地面和废弃物及时进行清理、消毒";
    String Item9_8 = "8.机械化挤奶设备完好";
    String Item9_9 = "9.贮奶罐温度保持在0-4℃、卫生符合要求";
    String Item9_10 = "10.生鲜乳检测设备完好";
    String Item9_11 = "11.冷链运输设备完好";
    String Item9_12 = "12.计量设备符合标准";
    String Item9_13 = "13.具有健康证明";
    String Item9_14 = "14.具有奶业方面的基本知识";
    String Item9_15 = "15.人员相对固定，数量配备合理";
    String Item9_16 = "16.有且留存生鲜乳每日收购记录";
    String Item9_17 = "17.有且留存生鲜乳每日销售记录";
    String Item9_18 = "18.有且留存生鲜乳质量检测记录";
    String Item9_19 = "19.有且留存挤贮奶设备清洗消毒记录";
    String Item9_20 = "20.有且留存异常奶处理记录";
    String Item9_21 = "21.与奶农签有生鲜乳购销合同";
    String Item9_22 = "22.与乳品加工企业签有原料奶购销合同";
    String Item9_23 = "23.有相应的质量管理办法";
    String Item9_24 = "24.有奶站管理和防疫制度";
    String Item9_25 = "25.实行分户留样制度";
    String Item9_26 = "26.使用生鲜乳交接单，并留存可查";

    String Item1_4_XZ1 = "距离生活饮用水源地少于500米";
    String Item1_4_XZ2 = "距离动物屠宰加工场所少于500米";
    String Item1_4_XZ3 = "距离动物及动物产品集贸市场少于500米";
    String Item1_4_XZ4 = "距离动物诊疗场所少于200米";
    String Item1_4_XZ5 = "距离种畜禽场少于1000米";
    String Item1_4_XZ6 = "距离动物养殖场（养殖小区）少于500米";
    String Item1_4_XZ7 = "距离动物隔离场所、无害化处理场所少于3000米";
    String Item1_4_XZ8 = "距离城镇居民区、文化教育科研等人口密集区少于500米";
    String Item1_4_XZ9 = "距离公路、铁路等主要交通干线少于500米";

    String Item1_9_XZ1 = "未建立免疫制度";
    String Item1_9_XZ2 = "未建立用药制度";
    String Item1_9_XZ3 = "未建立检疫申报制度";
    String Item1_9_XZ4 = "未建立疫情报告制度";
    String Item1_9_XZ5 = "未建立消毒制度";
    String Item1_9_XZ6 = "未建立无害化处理制度";
    String Item1_9_XZ7 = "未建立畜禽标识制度";

    String Item3_5_XZ1 = "动物医院未达到100平方米以上";
    String Item3_5_XZ2 = "动物诊所（门诊部）未达到60平方米以上";
    String Item3_5_XZ3 = "距离畜禽养殖场少于200米";
    String Item3_5_XZ4 = "距离屠宰加工场所应少于200米";
    String Item3_5_XZ5 = "距离动物交易场所少于200米[否]";

    String Item3_6_XZ1 = "未设有独立的出入口";
    String Item3_6_XZ2 = "诊疗室、手术室、药房等功能区布局不合理";
    String Item3_6_XZ3 = "兼营区域与诊疗区域未分别设置";

    String Item3_8_XZ1 = "未建立诊疗服务制度";
    String Item3_8_XZ2 = "未建立疫情报告制度";
    String Item3_8_XZ3 = "未建立卫生消毒制度";
    String Item3_8_XZ4 = "未建立兽药处方及药物管理制度";
    String Item3_8_XZ5 = "未建立病害组织及医疗废弃物无害化处理制度";

    String Item4_3_XZ1 = "距离动物养殖场、养殖小区低于3000米";
    String Item4_3_XZ2 = "距离动物屠宰加工场所低于3000米";
    String Item4_3_XZ3 = "距离动物隔离场所低于3000米";
    String Item4_3_XZ4 = "距离动物诊疗场所低于3000米";
    String Item4_3_XZ5 = "距离动物和动物产品集贸市场低于3000米";
    String Item4_3_XZ6 = "距离生活饮用水源地低于3000米";
    String Item4_3_XZ7 = "距离城镇居民区、文化教育科研等人口集中区域低于500米";
    String Item4_3_XZ8 = "距离公路、铁路等主要交通干线低于500米";

    String Item4_10_XZ1 = "未建立病害动物和动物产品入场登记制度";
    String Item4_10_XZ2 = "未建立消毒制度";
    String Item4_10_XZ3 = "未建立无害化处理制度";
    String Item4_10_XZ4 = "未建立无害化处理后的物品流向登记制度";
    String Item4_10_XZ5 = "未建立人员防护制度";

    String Item5_3_XZ1 = "距离动物养殖场（养殖小区）少于3000米";
    String Item5_3_XZ2 = "距离动物屠宰加工场所少于3000米";
    String Item5_3_XZ3 = "距离无害化处理场所少于3000米";
    String Item5_3_XZ4 = "距离动物诊疗场所少于3000米";
    String Item5_3_XZ5 = "距离动物及动物产品集贸市场少于3000米";
    String Item5_3_XZ6 = "距离其他动物隔离场所少于3000米";
    String Item5_3_XZ7 = "距离城镇居民区、文化教育科研等人口密集区少于500米";
    String Item5_3_XZ8 = "距离公路、铁路等主要交通干线少于500米";
    String Item5_3_XZ9 = "距离生活饮用水源地少于500米";

    String Item5_6_XZ1 = "未建立动物及动物产品进出登记";
    String Item5_6_XZ2 = "未建立免疫制度";
    String Item5_6_XZ3 = "未建立用药制度";
    String Item5_6_XZ4 = "未建立消毒制度";
    String Item5_6_XZ5 = "未建立疫情报告制度";
    String Item5_6_XZ6 = "未建立无害化处理制度";


    String ZHIFAQUERY_TABLE = "zhifaQuery_table";
    String ACTIVITY_NUM = "checkNumber";

    String dirName = "/11picture";
}
