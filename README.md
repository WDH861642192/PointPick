# android-push-from-mac
 最近恰好在做一个选取地址的功能，自己也在网上找了一些资料，发现大部分的例子都是只做到获取周边地址列表就结束了，我就自己写了，也参考了许多别人的代码。一些问题
  

> http://www.41443.com/HTML/Android/20150402/361162.html
> 别人写的，源码我没有，思路挺好

个人觉得特别重要的一点是，如果不是对百度地图的开发很熟悉，千万不要按照百度的官方文档去写，百度地图的SDK更新很快，但是文档跟不上，我建议，下载百度的demo ,按照demo里面的jar ，so 都copy过来。
  效果图
  强行安利一波母校，，嘎嘎
  ![这里写图片描述](http://img.blog.csdn.net/20160405190314746)

注释还是比较详细的，好多都是百度demo里面找出来的，一般的实现都是比较简单的，说一下，输入文字地址后的流程，需要注意的是，为了简单清晰，代码里面写了两个OnGetGeoCoderResultListener（虽然虽然一个只用地理编码，一个只用反地理编码），一个是用于手指拖动时，一个用于文字输入查找地址时，输入地址后，

```
	 @Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (s.length() > 2)
					mSearch.geocode(new GeoCodeOption().city("").address(
							s.toString()));
			}
```
由于这里不限制城市，city后面的参数传入空即可，然后进行地理编码

```
public void onGetGeoCodeResult(GeoCodeResult result) {
		// TODO Auto-generated method stub
		// if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR)
		// {
		// Toast.makeText(MainActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
		// .show();
		// return;
		// }
		if (result != null
				&& result.error.equals(SearchResult.ERRORNO.NO_ERROR)) {
			
		
			mLoactionLatLng = result.getLocation();
			turnBack();

		}
	}
```
看下turnBack（）方法

```
public void turnBack() {
		// 实现动画跳转
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(mLoactionLatLng);
		mBaiduMap.animateMapStatus(u);

		mBaiduMap.clear();
		// 发起反地理编码检索
		mGeoCoder.reverseGeoCode((new ReverseGeoCodeOption())
				.location(mLoactionLatLng));

	}
```
这里已经跳转到目标地址了，为什么还要进行反地理编码？？
那是因为，不仅需要能够条转到目标地址，还要获取其周边的地址，我下载了百度地图开发类的文档，
查了两种编码后返回的结果，分别如下
GeoCodeResult
![这里写图片描述](http://img.blog.csdn.net/20160405193017506)
ReverseGeoCodeResult
![这里写图片描述](http://img.blog.csdn.net/20160405193049741)
可以看到，ReverseGeoCodeResult里面是提供附近地址的返回的
然后更新Adapter里面的数据即可，，，

文档下载地址

> http://download.csdn.net/detail/qq_28224989/9481886

源码
> https://github.com/WDH861642192/android-push-from-mac/tree/master/BaiduMap
