# Plate

[中文文档](README.zh-CN.md)

Use for Android, a simple library for implementing dynamic configuration page.

--- 

## Feature

Suitable for dynamically configured pages, such as' active pages', 'operation pages',' home pages', and other pages that require from remote. 

- define remote view data
- present views

In 'Plate', configure the decision page.  

You need to define view data at the remote, then implement the `template` and `component` in the process of app development. 

---

## Demo

Build the `app` module and install for mobile

!['preview'](docs/img/demo_preview.gif)

---

## How to

Step 1. Gradle configure

Add the JitPack repository to your build file:

```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency：

```gradle
    implementation 'com.gitee.mingyueyixi:plate:v1.0.0'
```

Step 2、Init Plate

```kotlin
    Plate.doInit(context)
```

Step 3、Define the remote view data

The view data from the remote needs to conform to `plate` module, refer to: [PlateStructure](plate-core/src/main/java/com/lu/plate/data/PlateStructure.kt)

a sample like: [plate_sample_rv.json](app/src/main/assets/plate_sample_rv.json)

```json
{
  "contents": [
    {
      "templateId": 1,
      "id": 0,
      "props": {
        "clickLink": "app://schemas.plate.demo/app/web?url=https%3A%2F%2Fwww.biying.com",
        "title": "必应",
        "subTitle": "点击访问必应首页",
        "img": "https://cn.bing.com/rp/ar_9isCNU2Q-VG1yEDDHnx8HAFQ.png"
      },
      "style": {
        "background": {
          "shape": "rectangle",
          "solid": {
            "color": "#FFAA3399"
          }
        },
        "marginBottom": "100dp"
      }
    }
  ],
  "timeStamp": 0,
  "type": 0,
  "version": 0
}
```

Remember the `templateId` value, we'll use it later.

Step 4、Implement template and componen

```kotlin

class LeftImgLayoutTemplate(plate: Plate, templateId: Int) : BaseTemplate(plate, templateId) {

    override fun createSVComponent(parent: ViewGroup): SVComponent {
        throw NotImplementedError("LeftImgLayoutTemplate for scroll view component is not implement")
    }

    override fun createRVComponent(parent: ViewGroup, viewType: Int): RVComponent {
        return RVComponentImp(plate, viewType)
    }

    class RVComponentImp(plate: Plate, templateId: Int) : BaseRVComponent(plate, templateId) {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BasePlateRecyclerAdapter.BVH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LeftImgLayoutImgBinding.inflate(inflater, parent, false)
            return BasePlateRecyclerAdapter.BVH(this, binding.root)
        }

        override fun onBindView(
            adapter: BasePlateRecyclerAdapter,
            holder: BasePlateRecyclerAdapter.BVH,
            position: Int
        ) {
            super.onBindView(adapter, holder, position)
            val binding = LeftImgLayoutImgBinding.bind(holder.itemView)
            adapter.getItem(position)?.props?.let {
                applyHeaderImg(binding, it)
                binding.cardTitle.text = it.optString("title")
                binding.cardTitleSub.text = it.optString("subTitle")
            }

        }

        private fun applyHeaderImg(binding: LeftImgLayoutImgBinding, props: JsonObject) {
            val img = props.optString("img").ifBlank { null } ?: return
            Glide.with(binding.root.context)
                .load(img.trim())
                .disallowHardwareConfig()
                .into(binding.ivHeader)
        }

    }

}

```

Step 5、Register Template

register template with `templateId`:

```kotlin
val plate = Plate().also {
    it.register(LeftImgLayoutTemplate(it, 1))
}
```

Step 6、Apply for RecyclerView

Get a RecyclerView instance , then apply:

```kotlin

private fun initView() {
    val atapter = plate.createRecyclerAdapter(mPlateData).also {
        binding.recyclerView.adapter = it
        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    atapter.setOnClickListener { adapter, _, p ->
        val content = adapter.getItem(p)
        content?.props?.optString("clickLink").let {
            //跳转链接
            activity?.let { act ->
                AppLinkRouter.route(act, it)
            }
        }

    }

}

```

## LICENSE

[MulanPSL2](http://license.coscl.org.cn/MulanPSL2)


