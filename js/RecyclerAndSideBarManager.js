/**
 * Created by tfn on 2017/1/8.
 */
import {PropTypes} from 'react';

import {requireNativeComponent, View,} from 'react-native';

let RNRecyclerView = {
    propTypes: {
        dataJson: PropTypes.string,

        onItemHeader: PropTypes.func,
        onItemFocus: PropTypes.func,

        //添加默认View的属性，否则会导致各种属性未定义错误
        ...View.propTypes,
    },
};

module.exports = requireNativeComponent('RNRecyclerView', RNRecyclerView);