package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AnimZhongDanRedordBeangai;
import com.wyw.jiangsu.interfac.IAnimZhongDanRecordFragment;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public class AnimZhongDanRecordFragmentPresenter extends BasePresenter<IAnimZhongDanRecordFragment> {
    public AnimZhongDanRecordFragmentPresenter(IAnimZhongDanRecordFragment view) {
        super(view);
    }
private String fstId ="-1";
    public void refresh() {
        fstId ="-1";
        addSubscribe(NetClient.getAnimZhongDanRedordList(getUserId(),"V_Qua_QuarantineDeclarationCD_FZ",fstId)
        .map(animZhongDanRedordBean -> {
            List<AnimZhongDanRedordBeangai.DataListBean> dataListBeen = new StatusException(animZhongDanRedordBean).getObjectRefreshDataList(AnimZhongDanRedordBeangai.DataListBean.class);
            if (dataListBeen!=null&&dataListBeen.size()>0){
                fstId = dataListBeen.get(dataListBeen.size()-1).getFStId();
            }
            return dataListBeen;
        })
        .subscribe(dataListBeen -> getView().refresh(dataListBeen)
        ,new ExceptionImp(getView()),new CompleteImp(getView())));
    }

    public void loadMore(){
        addSubscribe(NetClient.getAnimZhongDanRedordList(getUserId(),"V_Qua_QuarantineDeclarationCD_FZ",fstId)
        .map(animZhongDanRedordBean -> {
            List<AnimZhongDanRedordBeangai.DataListBean>dataListBeen = new StatusException(animZhongDanRedordBean).getObjectDataList(AnimZhongDanRedordBeangai.DataListBean.class);
            if (dataListBeen!=null&&dataListBeen.size()>0){
                fstId = dataListBeen.get(dataListBeen.size()-1).getFStId();
            }
            return dataListBeen;
    })
      .subscribe(dataListBeen -> getView().loadMore(dataListBeen)
      ,new ExceptionImp(getView()),new CompleteImp(getView())));
    }
}
