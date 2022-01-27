package com.sargss.uatopnews.contracts

abstract class BaseContract {

    abstract class Presenter<T : View> {

        private var view: T? = null

        fun getView(): T {
            if (view == null) {
                throw Exception("Presenter View not initialized!")
            }

            return view as T
        }

        open fun attachView(view: T) {
            this.view = view
        }

        fun onDestroy() {
            this.view = null
        }
    }

    interface View {

    }
}