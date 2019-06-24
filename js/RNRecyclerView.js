/**
 * Created by tfn on 17-1-5.
 */

import React, {Component}from 'react';

import RCTMyCustomView from './RecyclerAndSideBarManager';

export default class RNRecyclerView extends Component {

    render() {
        return (
            <RCTMyCustomView
                {...this.props}
            />
        );
    }
}

