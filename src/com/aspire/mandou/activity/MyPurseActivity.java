package com.aspire.mandou.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aspire.mandou.activity.base.BaseActivity;
import com.aspire.mandou.framework.widget.NavigationHelper;
import com.aspire.mandou.util.CustomerUtil;
import com.aspire.mandou.util.IntentUtil;
import com.example.mandou.R;
import com.neweggcn.lib.json.JsonParseException;

public class MyPurseActivity extends BaseActivity {
	private LayoutInflater layoutInflater;
	private static final int MSG_NEWSLIST_GET = 1;
	private static final int PAGE_SIZE = 10;
	private int mCurrentPageNumber = 0;
//	private OrderFilter orderFilter;
//	private NLPullRefreshView mMyOrderListPullRefreshView;
	private ListView mMyOrderListView;
//	OrderListViewAdapter mAdapter;
	private View mMyOrderListEmptyView;

	private View tabCardContainerView;
	private ImageView tabCardImageView;
	private TextView tabCardTextView;
	private View tabCardLineView;
	private View tabCardLayout;

	private View tabOrderContainerView;
	private ImageView tabOrderImageView;
	private TextView tabOrderTextView;
	private View tabOrderLineView;
	private View tabOrderLayout;

	private List<TextView> pointTextViewList;

//	private NLPullRefreshView cardListPullRefreshView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		putContentView(R.layout.my_purse_layout, R.string.my_purse_title);
		CustomerUtil.addVisitorView(this);
		layoutInflater = (LayoutInflater) getSystemService(Service.LAYOUT_INFLATER_SERVICE);
//		initViews();
//		getBannerList();
//		getMyCardList();
	}

	@Override
	public void onBackPressed() {
//		ExitAppUtil.exit(MyPurseActivity.this);
	}

	// 添加信息卡按钮事件
	public void onClickAddCard(View view) {
		IntentUtil.redirectToNextActivity(this, AddCardActivity.class);
	}

	private void initViews() {
//		// 我的银行卡
//		tabCardContainerView = findViewById(R.id.my_card_framelayout);
//		tabCardImageView = (ImageView) findViewById(R.id.my_card_image);
//		tabCardTextView = (TextView) findViewById(R.id.my_card_textview);
//		tabCardLineView = findViewById(R.id.my_card_line_selected_view);
//		tabCardLayout = findViewById(R.id.my_card_layout);
//
//		// 我的电子券
//		tabOrderContainerView = findViewById(R.id.my_order_framelayout);
//		tabOrderImageView = (ImageView) findViewById(R.id.my_order_image);
//		tabOrderTextView = (TextView) findViewById(R.id.my_order_textview);
//		tabOrderLineView = findViewById(R.id.my_order_line_selected_view);
//		tabOrderLayout = findViewById(R.id.my_order_layout);

		tabCardContainerView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectTabCard();
				if (tabCardLayout.getTag() == null) {
//					getMyCardList();
				}
			}
		});

		tabOrderContainerView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectTabOrder();
				if (tabOrderLayout.getTag() == null) {
//					getMyOrderList();
				}
			}
		});

//		mMyOrderListView = (ListView) findViewById(R.id.my_order_list);
//		mMyOrderListPullRefreshView = (NLPullRefreshView) findViewById(R.id.my_order_list_container);
//		mMyOrderListPullRefreshView.setRefreshListener(new RefreshListener() {
//			
//			@Override
//			public void onRefresh(NLPullRefreshView view) {
//				getMyOrderList();
//			}
//		});
//
//		mMyOrderListEmptyView = findViewById(R.id.my_order_list_empty);
//
//		cardListPullRefreshView = (NLPullRefreshView)findViewById(R.id.my_card_list_container);
//		
//		cardListPullRefreshView.setRefreshListener(new RefreshListener() {
//			
//			@Override
//			public void onRefresh(NLPullRefreshView view) {
//				getMyCardList();
//			}
//		});
	}

	private void selectTabCard() {
		// 我的银行卡
		tabCardImageView.setImageResource(R.drawable.card_hl);
		tabCardTextView
				.setTextColor(getResources().getColor(R.color.menu_gray));
		tabCardLineView.setVisibility(View.VISIBLE);
		tabCardLayout.setVisibility(View.VISIBLE);

		// 我的电子券
		tabOrderImageView.setImageResource(R.drawable.ticket_nor);
		tabOrderTextView.setTextColor(getResources().getColor(
				R.color.menu_light_gray));
		tabOrderLineView.setVisibility(View.GONE);
		tabOrderLayout.setVisibility(View.GONE);
	}

	private void selectTabOrder() {
		// 我的银行卡
		tabCardImageView.setImageResource(R.drawable.card_nor);
		tabCardTextView.setTextColor(getResources().getColor(
				R.color.menu_light_gray));
		tabCardLineView.setVisibility(View.GONE);
		tabCardLayout.setVisibility(View.GONE);

		// 我的电子券
		tabOrderImageView.setImageResource(R.drawable.ticket_hl);
		tabOrderTextView.setTextColor(getResources()
				.getColor(R.color.menu_gray));
		tabOrderLineView.setVisibility(View.VISIBLE);
		tabOrderLayout.setVisibility(View.VISIBLE);
	}

//	private View generateCardView(final CreditCardInfo cardInfo) {
//		View rootView = layoutInflater.inflate(R.layout.my_purse_card_cell,
//				null);
//		TextView bankName = (TextView) rootView.findViewById(R.id.bank_name);
//		TextView bankNumber = (TextView) rootView
//				.findViewById(R.id.bank_number);
//		TextView bankPoint = (TextView) rootView.findViewById(R.id.bank_point);
//
//		// 银行名称
//		bankName.setText(cardInfo.getBankName());
//		// 尾号
//		String cardNumberString = cardInfo.getCardNumber();
//		if (cardNumberString == null) {
//			cardNumberString = "";
//		}
//		if (cardNumberString.length() > 4) {
//			cardNumberString = cardNumberString.substring(cardNumberString
//					.length() - 4);
//		}
//		String numberString = getString(R.string.my_purse_point_wei_hao,
//				cardNumberString);
//		bankNumber.setText(numberString);
//		// 积分数量
//		setPointTextView(bankPoint, "加载中...");
//		bankPoint.setTag(cardInfo.getSysNo());
//		pointTextViewList.add(bankPoint);
//
//		Button opUnbind = (Button) rootView.findViewById(R.id.op_unbind);
//		opUnbind.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Dialog dialog = buildUnbindConfirmDialog(cardInfo);
//				dialog.show();
//			}
//		});
//
//		return rootView;
//	}

//	private Dialog buildUnbindConfirmDialog(final CreditCardInfo cardInfo) {
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setTitle("温馨提示");
//		builder.setMessage("确认解绑此银行卡吗？");
//		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				unbindCard(cardInfo);
//			}
//		});
//
//		builder.setNegativeButton("取消", null);
//
//		return builder.create();
//	}

	private void setPointTextView(TextView textView, String point) {
		String pointString = getString(R.string.my_purse_point_count, point);
		textView.setText(pointString);
	}

//	private void unbindCard(CreditCardInfo cardInfo) {
//		final CardUnbindRequest request = new CardUnbindRequest();
//		request.setCardSysNo(cardInfo.getSysNo());
//		request.setCardNo(cardInfo.getCardNumber());
//		new MyAsyncTask<ResultData<CardUnbindRequest>>(this) {
//
//			@Override
//			public ResultData<CardUnbindRequest> callService()
//					throws IOException, JsonParseException, BizException,
//					ServiceException {
//				return new WalletService().unbindCard(request);
//			}
//
//			@Override
//			public void onLoaded(ResultData<CardUnbindRequest> result)
//					throws Exception {
//				MyToast.show(MyPurseActivity.this, result.getMessage());
//				if (result.isSuccess()) {
//					// 刷新我的信用卡列表
//					getMyCardList();
//				} else {
//					showError(result);
//				}
//			}
//
//		}.execute();
//	}

//	private void getMyCardList() {
//		showLoading("正在加载信用卡信息...");
//		new MyAsyncTask<ResultData<List<CreditCardInfo>>>(this) {
//
//			@Override
//			public ResultData<List<CreditCardInfo>> callService()
//					throws IOException, JsonParseException, BizException,
//					ServiceException {
//				return new WalletService().getMyCardList();
//			}
//
//			@Override
//			public void onLoaded(ResultData<List<CreditCardInfo>> result)
//					throws Exception {
//				cardListPullRefreshView.finishRefresh();
//				closeLoading();
//				if (result.isSuccess()) {
//					showCardList(result.getData());
//					getMyCardPointList(result.getData());
//					tabCardLayout.setTag("loaded");
//				} else {
//					showError(result);
//				}
//			}
//
//		}.execute();
//	}
//
//	private void getMyCardPointList(List<CreditCardInfo> cardList) {
//		if (cardList == null || cardList.size() == 0) {
//			return;
//		}
//		final CreditCardPointFilter filter = new CreditCardPointFilter();
//		List<CreditCardPointFilterItem> filterItems = new ArrayList<CreditCardPointFilterItem>();
//		filter.setCardInfoList(filterItems);
//		for (CreditCardInfo cardInfo : cardList) {
//			CreditCardPointFilterItem item = new CreditCardPointFilterItem();
//			item.setCardSysNo(cardInfo.getSysNo());
//			item.setCardNo(cardInfo.getCardNumber());
//			filterItems.add(item);
//		}
//
//		showLoading("正在加载信用卡积分...");
//		new MyAsyncTask<ResultData<List<CreditCardPointResult>>>(this) {
//
//			@Override
//			public ResultData<List<CreditCardPointResult>> callService()
//					throws IOException, JsonParseException, BizException,
//					ServiceException {
//				return new WalletService().getCardPointList(filter);
//			}
//
//			@Override
//			public void onLoaded(ResultData<List<CreditCardPointResult>> result)
//					throws Exception {
//				closeLoading();
//				if (result.isSuccess()) {
//					showCardPoint(result.getData());
//				} else {
//					showError(result);
//				}
//			}
//
//		}.execute();
//	}
//
//	private void showCardPoint(List<CreditCardPointResult> cardList) {
//		double total = 0;
//		for (TextView textView : pointTextViewList) {
//			CreditCardPointResult foundCardPointResult = null;
//			for (CreditCardPointResult itemResult : cardList) {
//				if (textView.getTag().toString()
//						.equals(String.valueOf(itemResult.getCardSysNo()))) {
//					foundCardPointResult = itemResult;
//					break;
//				}
//
//			}
//			if (foundCardPointResult != null) {
//				setPointTextView(textView,
//						String.valueOf(foundCardPointResult.getPoint()));
//				total += foundCardPointResult.getPointValue();
//			}
//		}
//
//		TextView totalTextView = (TextView) findViewById(R.id.my_purse_point_total);
//		String totalString = getString(R.string.my_purse_point_total,
//				StringUtil.priceToString(total));
//		totalTextView.setText(totalString);
//	}
//
//	private void showCardList(List<CreditCardInfo> cardList) {
//
//		LinearLayout layout = (LinearLayout) findViewById(R.id.my_card_list);
//
//		View emptyTextView = findViewById(R.id.my_card_list_empty);
//		TextView totalTextView = (TextView) findViewById(R.id.my_purse_point_total);
//		layout.removeAllViews();
//		pointTextViewList = new ArrayList<TextView>();
//		if (cardList == null || cardList.size() == 0) {
//			// 显示空列表
//			emptyTextView.setVisibility(View.VISIBLE);
//			cardListPullRefreshView.setVisibility(View.GONE);
//			totalTextView.setVisibility(View.GONE);
//			return;
//		} else {
//			emptyTextView.setVisibility(View.GONE);
//			cardListPullRefreshView.setVisibility(View.VISIBLE);
//			totalTextView.setVisibility(View.VISIBLE);
//		}
//		for (int i = 0; i < cardList.size(); i++) {
//			View rootView = generateCardView(cardList.get(i));
//			layout.addView(rootView);
//		}
//	}
//
//	private void getMyOrderList() {
//		mCurrentPageNumber = 0;
//		if(mAdapter != null){
//			//在刷新前，先清空原来的数据
//			mAdapter.clear();
//		}
//		final Handler mHandler = new Handler(new Callback() {
//
//			@Override
//			public boolean handleMessage(Message msg) {
//				if (msg.what == MSG_NEWSLIST_GET) {
//					int hasData = (Integer) msg.obj;
//					if (hasData == 1) {
//						showOrderList();
//					} else {
//						showOrderListEmpty();
//					}
//					mMyOrderListPullRefreshView.finishRefresh();
//				}
//				return false;
//			}
//		});
//
//		CBCollectionResolver<OrderInfo> mResolver = new CBCollectionResolver<OrderInfo>() {
//			@Override
//			public HasCollection<OrderInfo> query() throws IOException,
//					ServiceException, BizException {
//				if (orderFilter == null) {
//					orderFilter = new OrderFilter();
//				}
//				orderFilter.getPageInfo().setPageIndex(mCurrentPageNumber);
//				orderFilter.getPageInfo().setPageSize(PAGE_SIZE);
//				ResultData<OrderListInfo> resultData = new WalletService()
//						.getOrderListInfo(orderFilter);
//				OrderListInfo info = resultData.getData();
//				if (info != null && info.getList() != null
//						&& info.getList().size() > 0) {
//					if (mCurrentPageNumber == 0) {
//						Message msg = new Message();
//						msg.what = MSG_NEWSLIST_GET;
//						msg.obj = 1;
//						mHandler.sendMessage(msg);
//					}
//					mCurrentPageNumber += 1;
//
//				} else {
//					Message msg = new Message();
//					msg.what = MSG_NEWSLIST_GET;
//					msg.obj = 0;
//					mHandler.sendMessage(msg);
//				}
//
//				return info;
//			}
//		};
//
//		mAdapter = new OrderListViewAdapter(this);
//		
//		mMyOrderListView.setAdapter(mAdapter);
//		CollectionStateObserver observer = new CollectionStateObserver();
//		mAdapter.setVisible(true);
//		observer.setActivity(this);
//		observer.setAdapters(mAdapter);
//		mAdapter.startQuery(mResolver);
//		mMyOrderListView
//				.setOnScrollListener(new MyDecoratedAdapter.ListScrollListener(
//						mAdapter, mResolver));
//
//		tabOrderLayout.setTag("loaded");
//	}
//
//	private void showOrderList() {
//		mMyOrderListPullRefreshView.setVisibility(View.VISIBLE);
//		mMyOrderListEmptyView.setVisibility(View.GONE);
//	}
//
//	private void showOrderListEmpty() {
//		mMyOrderListPullRefreshView.setVisibility(View.GONE);
//		mMyOrderListEmptyView.setVisibility(View.VISIBLE);
//	}
//
//	private void getBannerList() {
//
//		new MyAsyncTask<ResultData<List<BannerInfo>>>(this) {
//
//			@Override
//			public ResultData<List<BannerInfo>> callService()
//					throws IOException, JsonParseException, BizException,
//					ServiceException {
//				return new MostBargainService().getWalletBanner();
//			}
//
//			@Override
//			public void onLoaded(ResultData<List<BannerInfo>> result)
//					throws Exception {
//				if (result.isSuccess() && result.getData() != null) {
//					setViewPager(result.getData());
//				} else if (!StringUtil.isEmpty(result.getMessage())) {
//					showError(result);
//					// 请求banner失败，就隐藏
//					FrameLayout mViewPagerFrameLayout = (FrameLayout) findViewById(R.id.product_list_viewpager_framelayout);
//					mViewPagerFrameLayout.setVisibility(View.GONE);
//				}
//			}
//
//		}.execute();
//	}
//
//	private void setViewPager(List<BannerInfo> bannerInfoList) {
//		ViewPager mViewPager = (ViewPager) findViewById(R.id.product_list_viewpager);
//		final RadioGroup mRadioGroup = (RadioGroup) findViewById(R.id.product_list_radiogroup);
//		FrameLayout mViewPagerFrameLayout = (FrameLayout) findViewById(R.id.product_list_viewpager_framelayout);
//
//		if (bannerInfoList == null || bannerInfoList.size() == 0) {
//			mViewPagerFrameLayout.setVisibility(View.GONE);
//			bannerInfoList = new ArrayList<BannerInfo>();
//		} else {
//			mViewPagerFrameLayout.setVisibility(View.VISIBLE);
//		}
//		MyPurseBannerPagerAdapter mViewPagerAdapter = new MyPurseBannerPagerAdapter(
//				this, bannerInfoList);
//		mViewPager.setAdapter(mViewPagerAdapter);
//		if (bannerInfoList.size() > 1) {
//			generateIndicator(mRadioGroup, bannerInfoList.size(),
//					R.drawable.radio_check_selector);
//		}
//		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
//
//			@Override
//			public void onPageSelected(int arg0) {
//				mRadioGroup.check(arg0);
//			}
//
//			@Override
//			public void onPageScrolled(int arg0, float arg1, int arg2) {
//			}
//
//			@Override
//			public void onPageScrollStateChanged(int arg0) {
//			}
//		});
//	}
//
//	private void generateIndicator(RadioGroup radioGroup, int size, int selector) {
//		radioGroup.removeAllViews();
//		for (int i = 0; i < size; i++) {
//			RadioButton radioButton = new RadioButton(this);
//			radioButton.setId(i);
//			radioButton.setButtonDrawable(android.R.color.transparent);
//			radioButton.setBackgroundResource(selector);
//			radioButton.setClickable(false);
//			int radius = DisplayUtil.getPxByDp(this, 8);
//			int margin = DisplayUtil.getPxByDp(this, 3);
//			RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(radius,
//					radius);
//			lp.setMargins(margin, margin, margin, margin);
//			radioGroup.addView(radioButton, lp);
//		}
//		radioGroup.clearCheck();
//		radioGroup.check(0);
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		if (PayCompleteNoticeUtil.isRefresh(PayCompleteNoticeUtil.HOME)) {
//			PayCompleteNoticeUtil.setRefresh(PayCompleteNoticeUtil.HOME);
//			getMyCardList();
//			if(tabOrderLayout.getTag() != null){
//				getMyOrderList();
//			}
//		}
//	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
//		getMyCardList();
	}

	@Override
	protected void onDestroy() {
		CustomerUtil.removeVisitorView(this);
		super.onDestroy();
	}
}
