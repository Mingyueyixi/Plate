package com.lu.plate.util.resolve

class StyleComposite {


    companion object {
        internal var IMP = object : IMPInterface {}
        val size = IMP.getSizeResolve()
        val background = IMP.getBackgroundResolve()
        val color = IMP.getColorResolve()

    }

    interface IMPInterface {
        fun getSizeResolve(): SizeResolve = SizeResolve()
        fun getBackgroundResolve(): BackgroundResolve = BackgroundResolve()
        fun getColorResolve(): ColorResolve = ColorResolve()
    }

}