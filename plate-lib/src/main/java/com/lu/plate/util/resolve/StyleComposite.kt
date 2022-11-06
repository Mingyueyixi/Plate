package com.lu.plate.util.resolve

class StyleComposite {


    companion object {
        internal var IMP = object : IMPInterface {}
        val size :SizeResolve get() = IMP.getSizeResolve()
        val background get() =  IMP.getBackgroundResolve()
        val color get() =  IMP.getColorResolve()

    }

    interface IMPInterface {
        fun getSizeResolve(): SizeResolve = SizeResolve()
        fun getBackgroundResolve(): BackgroundResolve = BackgroundResolve()
        fun getColorResolve(): ColorResolve = ColorResolve()
    }

}