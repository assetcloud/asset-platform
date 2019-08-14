package com.asset.entity;

import lombok.Data;
import org.checkerframework.checker.units.qual.min;

import java.util.List;

/**
 * 在FormItemBase中的options字段，下面的这两个字段是一定存在的
 *
 * @author YBY
 * @version 1.0_190712
 * @time 190712
 */
@Data
public class OptionsBase {
    Boolean required;
    Boolean disabled;
    Object width;
    Object defaultValue;
    Object dataType;
    Object pattern;
    Object placeholder;
    Object remoteFunc;
    Object remote;
    List<Object> remoteOptions;
    Object props;
    List<Object> options;
    Object showLabel;
    Object inline;
    Object range;
    Object min;
    Object max;
    Object step;
    Object showInput;
    Object multiple;
    Object clearable;
    Object filterable;
    Object showAlpha;
    Object allowHalf;
    Object readonly;
    Object editable;
    Object startPlaceholder;
    Object endPlaceholder;
    Object type;
    Object format;
    Object timestamp;
    Object isRange;
    Object arrowControl;
    Object controlsPosition;
    Object customClass;

    public OptionsBase() {
    }
}
