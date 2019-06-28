package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.interfac.IAnimalAQueryDetilActivity;

/**
 * Created by wyw on 2017/7/17.
 */

public class AnimalAQueryDetilActivityPresenter extends BasePresenter<IAnimalAQueryDetilActivity>{

    public AnimalAQueryDetilActivityPresenter(IAnimalAQueryDetilActivity view) {
        super(view);
    }

//    public void uploadPrint(String type,String id,String tname) {
//        addSubscribe(NetClient.uploadPrint(type,id,tname,"1")
//                .doOnSubscribe(new DoOnSubscriber(getView()))
//                .map(BaseMsgBean -> {
//                    if (BaseMsgBean.getErrorCode()!=0){
//                        Toast.makeText(MyApplication.getContext(), "打印失败", Toast.LENGTH_SHORT).show();
//                        getView().Return();
//                        throw new IllegalArgumentException(BaseMsgBean.getErrorMsg());
//                    }else {
//                        Toast.makeText(MyApplication.getContext(), "打印成功", Toast.LENGTH_SHORT).show();
//                    }
//                    return BaseMsgBean.getData().getResult();
//                }).subscribe(DataListBean ->getView().Toath()
//                        , new ExceptionImp(getView()), new CompleteImp(getView())));
//    }
}
