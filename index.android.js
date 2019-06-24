/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';
import {
    AppRegistry, findNodeHandle, UIManager
} from 'react-native';

import RNRecyclerView from './js/RNRecyclerView'

export default class NativeUIDemo extends Component {

    render() {
        let dataJson = [
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frv03m8ky5j30iz0rltfp.jpg",
                "item_id": "item_id_xx_0",
                "item_name": "孙立"
            },
            {
                "attention_status": "1",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frsllc19gfj30k80tfah5.jpg",
                "item_id": "item_id_xx_1",
                "item_name": "宣赞"
            },
            {
                "attention_status": "1",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frslibvijrj30k80q678q.jpg",
                "item_id": "item_id_xx_2",
                "item_name": "郝思文"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frrifts8l5j30j60ojq6u.jpg",
                "item_id": "item_id_xx_3",
                "item_name": "韩滔"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frqscr5o00j30k80qzafc.jpg",
                "item_id": "item_id_xx_4",
                "item_name": "彭玘"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frmuto5qlzj30ia0notd8.jpg",
                "item_id": "item_id_xx_5",
                "item_name": "单廷珪"
            },
            {
                "attention_status": "1",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frjd77dt8zj30k80q2aga.jpg",
                "item_id": "item_id_xx_6",
                "item_name": "魏定国"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frjd4var2bj30k80q0dlf.jpg",
                "item_id": "item_id_xx_7",
                "item_name": "萧让"
            },
            {
                "attention_status": "1",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frja502w5xj30k80od410.jpg",
                "item_id": "item_id_xx_8",
                "item_name": "裴宣"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1fri9zqwzkoj30ql0w3jy0.jpg",
                "item_id": "item_id_xx_9",
                "item_name": "欧鹏"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frg40vozfnj30ku0qwq7s.jpg",
                "item_id": "item_id_xx_10",
                "item_name": "邓飞"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frevscw2wej30je0ps78h.jpg",
                "item_id": "item_id_xx_11",
                "item_name": " 燕顺"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frepozc5taj30qp0yg7aq.jpg",
                "item_id": "item_id_xx_12",
                "item_name": "杨林"
            },
            {
                "attention_status": "1",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frepq6mfvdj30p00wcwmq.jpg",
                "item_id": "item_id_xx_13",
                "item_name": "凌振"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frepqtwifwj30no0ti47n.jpg",
                "item_id": "item_id_xx_14",
                "item_name": "蒋敬"
            },
            {
                "attention_status": "1",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frepr2rhxvj30qo0yjth8.jpg",
                "item_id": "item_id_xx_15",
                "item_name": "吕方"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1freprc128lj30sg15m12u.jpg",
                "item_id": "item_id_xx_16",
                "item_name": "郭 盛"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1freprk6sd7j30sg15h7d2.jpg",
                "item_id": "item_id_xx_17",
                "item_name": "安道全"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1freprsjjwmj30sg15dth0.jpg",
                "item_id": "item_id_xx_18",
                "item_name": "皇甫端"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1freps07ubij30sg1dgwr7.jpg",
                "item_id": "item_id_xx_19",
                "item_name": "王英"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1freps89wc7j30no0uk45j.jpg",
                "item_id": "item_id_xx_20",
                "item_name": "扈三娘"
            },
            {
                "attention_status": "1",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frepsi3o15j30k80oidkd.jpg",
                "item_id": "item_id_xx_21",
                "item_name": "鲍旭"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frepspsrhyj30ku0qsjuc.jpg",
                "item_id": "item_id_xx_22",
                "item_name": "樊瑞"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frepsy47grj30qo0y97en.jpg",
                "item_id": "item_id_xx_23",
                "item_name": "孔明"
            },
            {
                "attention_status": "0",
                "item_cover": "http://ww1.sinaimg.cn/large/0065oQSqly1frept5di16j30p010g0z9.jpg",
                "item_id": "item_id_xx_24",
                "item_name": "孔亮"
            },
            {
                "attention_status": "0",
                "item_cover": "https://ws1.sinaimg.cn/large/610dc034ly1fp9qm6nv50j20u00miacg.jpg",
                "item_id": "item_id_xx_25",
                "item_name": "项充"
            }];
        return (
            <RNRecyclerView
                style={{flex: 1}}
                ref={'RNRecyclerView'}
                dataJson={JSON.stringify(dataJson)}
                onItemFocus={(data) => {
                    this.sendNotification(data.nativeEvent);
                }}
                onItemHeader={(data) => {
                    alert("header: " + JSON.stringify(data.nativeEvent));
                }}
            />
        );
    }

    sendNotification = (native) => {
        // //向native层发送命令
        UIManager.dispatchViewManagerCommand(
            findNodeHandle(this.refs.RNRecyclerView),
            UIManager.RNRecyclerView.Commands.js2native,
            [native.item_id, native.attention_status == '1' ? '0' : '1'],
        );
    }
}
AppRegistry.registerComponent('NativeUIDemo', () => NativeUIDemo);