package com.turing.newaomo.davinsbrush.fragment.gen_daily;

import com.turing.newaomo.davinsbrush.fragment.gen_daily.base.BasePresenter;
import com.turing.newaomo.davinsbrush.fragment.gen_daily.base.BaseView;

/**
 * Created by andy (https://github.com/andyxialm)
 * Creation time: 16/11/2 18:18
 * Description  : Sample contract
 */
public interface SampleContract {

    interface View extends BaseView<SamplePresenter> {

        void setTextData(String text);

        void startTyper();

        boolean isActive();
    }

    interface SamplePresenter extends BasePresenter {

        /**
         * request get text data
         */
        void loadTextData(String text);
    }
}
