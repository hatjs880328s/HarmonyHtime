package com.inspur.provider;

import ohos.aafwk.ability.fraction.*;
import ohos.agp.components.*;
import ohos.utils.PlainArray;

import java.util.Optional;

public abstract class BarComponentProvider {

    /**
     * 当前的fraction
     */
    protected Fraction mCurFraction;
    /**
     * fraction管理器
     */
    protected FractionManager mFractionManager;

    private PlainArray<Fraction> mFractionPlainArray;

    public BarComponentProvider(FractionManager fractionManager) {
        mFractionManager = fractionManager;
        mFractionPlainArray = new PlainArray<>();
    }

    /**
     * 创建fraction，并对fraction进行管理
     *
     * @param container
     * @param position
     */
    public void createPageInContainer(ComponentContainer container, int position) {
        // 开启事务
        FractionScheduler fractionScheduler = mFractionManager.startFractionScheduler();
        if (mCurFraction != null) {
            // 当前的fraction不为空，就隐藏
            fractionScheduler.hide(mCurFraction);
        }
        String tag = container.getId() + ":" + position;
        Fraction fraction;
        // 根据标签从FractionManager里面获取fraction
        Optional<Fraction> fractionOptional = mFractionManager.getFractionByTag(tag);
        if (fractionOptional.isPresent()) {
            fraction = fractionOptional.get();
            // 获取的fraction不为空，显示出来
            fractionScheduler.show(fraction);
        } else {
            // 获取的fraction为空，创建fraction
            fraction = getPage(position);
            // 将fraction添加到fractionScheduler
            fractionScheduler.add(container.getId(), fraction, tag);
        }
        mCurFraction = fraction;
        // 提交事务
        fractionScheduler.submit();
    }

    /**
     * 根据位置创建fraction对象
     *
     * @param position 位置
     * @return fraction对象
     */
    public Fraction getPage(int position) {
        // 从缓存获取fraction对象
        Optional<Fraction> fractionOptional = mFractionPlainArray.get(position);
        if (fractionOptional.isPresent()) {
            // 存在，直接返回
            return fractionOptional.get();
        }
        // 不存在fraction对象，则让子类通过反射创建fraction对象
        Fraction fraction = getFraction(position);
        // 将创建好点的对象添加到缓存
        mFractionPlainArray.put(position, fraction);
        return fraction;
    }

    public abstract Fraction getFraction(int position);

    /**
     * fragment的总数
     *
     * @return fragment的总数
     */
    public abstract int getCount();

    /**
     * 获取当前的fraction
     *
     * @return 当前的fraction
     */
    public Fraction getCurrentFraction() {
        return mCurFraction;
    }
}