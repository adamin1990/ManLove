package com.adamin.superrecyclerview;

/**
 ItemAnimator

 Step 1

 Set RecyclerView ItemAnimator.

 RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
 recyclerView.setItemAnimator(new SlideInLeftAnimator());
 RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
 recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f));
 Step 2

 Please use the notifyItemRemoved and notifyItemInserted.

 public void remove(int position) {
 mDataSet.remove(position);
 notifyItemRemoved(position);
 }

 public void add(String text, int position) {
 mDataSet.add(position, text);
 notifyItemInserted(position);
 }
 Advanced Step 3

 You can change the durations.

 recyclerView.getItemAnimator().setAddDuration(1000);
 recyclerView.getItemAnimator().setRemoveDuration(1000);
 recyclerView.getItemAnimator().setMoveDuration(1000);
 recyclerView.getItemAnimator().setChangeDuration(1000);
 Advanced Step 4

 Change the interpolator.

 SlideInLeftAnimator animator = new SlideInLeftAnimato();
 animator.setInterpolator(new OvershootInterpolator());
 // or recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f));
 recyclerView.setItemAnimator(animator);
 Advanced Step 5

 By extending AnimateViewHolder, you can override preset animation.
 So, custom animation can be set depending on view holder.

 static class MyViewHolder extends AnimateViewHolder {

 public MyViewHolder(View itemView) {
 super(itemView);
 }

 @Override
 public void animateRemoveImpl(ViewPropertyAnimatorListener listener) {
 ViewCompat.animate(itemView)
 .translationY(-itemView.getHeight() * 0.3f)
 .alpha(0)
 .setDuration(300)
 .setListener(listener)
 .start();
 }

 @Override
 public void preAnimateAddImpl() {
 ViewCompat.setTranslationY(itemView, -itemView.getHeight() * 0.3f);
 ViewCompat.setAlpha(itemView, 0);
 }

 @Override
 public void animateAddImpl(ViewPropertyAnimatorListener listener) {
 ViewCompat.animate(itemView)
 .translationY(0)
 .alpha(1)
 .setDuration(300)
 .setListener(listener)
 .start();
 }
 }
 Animators

 Cool

 LandingAnimator

 Scale

 ScaleInAnimator, ScaleInTopAnimator, ScaleInBottomAnimator
 ScaleInLeftAnimator, ScaleInRightAnimator

 Fade

 FadeInAnimator, FadeInDownAnimator, FadeInUpAnimator
 FadeInLeftAnimator, FadeInRightAnimator

 Flip

 FlipInTopXAnimator, FlipInBottomXAnimator
 FlipInLeftYAnimator, FlipInRightYAnimator

 Slide

 SlideInLeftAnimator, SlideInRightAnimator, OvershootInLeftAnimator, OvershootInRightAnimator
 SlideInUpAnimator, SlideInDownAnimator

 RecyclerView.Adapter

 Step 1

 Set RecyclerView ItemAnimator.

 RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
 MyAdapter adapter = new MyAdapter();
 recyclerView.setAdapter(new AlphaInAnimationAdapter(adapter));
 Advanced Step 2

 Change the durations.

 MyAdapter adapter = new MyAdapter();
 AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
 alphaAdapter.setDuration(1000);
 recyclerView.setAdapter(alphaAdapter);
 Advanced Step 3

 Change the interpolator.

 MyAdapter adapter = new MyAdapter();
 AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
 alphaAdapter.setInterpolator(new OvershootInterpolator());
 recyclerView.setAdapter(alphaAdapter);
 Advanced Step 4

 Disable the first scroll mode.

 MyAdapter adapter = new MyAdapter();
 AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
 scaleAdapter.setFirstOnly(false);
 recyclerView.setAdapter(alphaAdapter);
 Advanced Step 5

 Multiple Animations

 MyAdapter adapter = new MyAdapter();
 AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
 recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
 Adapters

 Alpha

 AlphaInAnimationAdapter

 Scale

 ScaleInAnimationAdapter

 Slide

 SlideInBottomAnimationAdapter
 SlideInRightAnimationAdapter, SlideInLeftAnimationAdapter
 */
public class Usage {
    /*
    * The following is the simplest usage. Drawing a divider drawable retrieved from android.R.attr.listDivider between each cell.

RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
If you want to set color, size and margin values, you can specify as the followings.

RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
recyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(this)
                .color(Color.RED)
                .sizeResId(R.dimen.divider)
                .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
                .build());
Instead of setting color and size, you can set paint object.

Paint paint = new Paint();
paint.setStrokeWidth(5);
paint.setColor(Color.BLUE);
paint.setAntiAlias(true);
paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
recyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());
Also 9patch drawable can be used for drawing divider.

RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
        .drawable(R.drawable.sample)
        .size(15)
        .build());
If you want to customize divider depending on the position, implement the following interfaces.

List of provider

The following providers can be implemented and controllable for each divider drawn between cells.

ColorProvider Provide color for divider

PaintProvider Provide paint object for divider line to draw.

DrawableDivider Provide drawable object for divider line

SizeProvider Provide height for horizontal divider, width for vertical divider.

VisibilityProvider
Enables you to control the visibility of dividers.

MarginProvider for horizontal divider (vertical list)
Enables you to specify left and right margin of divider.

MarginProvider for vertical divider (horizontal list)
Enables you to specify top and bottom margin of divider.

Please refer to ComplexAdapter class in the sample for the usage of providers in detail.

Optional

The following method can be used if you want to draw divider line at the end of last item in RecyclerView. If you enable this, the range of position parameter of providers listed above is 0 to itemCount-1. Otherwise, the range is 0 to itemCount-2.

FlexibleDividerDecoration.Builder.showLastDivider*/
}
