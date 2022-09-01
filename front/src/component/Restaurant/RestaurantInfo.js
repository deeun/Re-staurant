import React from "react";
import styles from "./RestaurantInfo.module.css";
import { RenderAfterNavermapsLoaded, NaverMap, Marker } from "react-naver-maps";

const RestaurantInfo = (props) => {
  const position = { lat: props.restaurant.latitude, lng: props.restaurant.longitude };
  return (
    <div id="res-info" className={styles.container}>
      <div>
        <h3>영업시간</h3>
        <p>{props.restaurant.bizhourInfo}</p>
      </div>
      <div>
        <h3>편의시설</h3>
        {/* <div>
          {props.restaurant.options.map((option) => {
            return <img key={option.id} src={option.iconURL} alt={option.name} />;
          })}
        </div> */}
      </div>
      <div>
        <h3>전화번호</h3>
        <p>{props.restaurant.phone}</p>
      </div>
      <div>
        <h3>SNS</h3>
        <p>{props.restaurant.sns}</p>
      </div>
      <div>
        <h3>위치정보</h3>
        <p>{props.restaurant.fullRoadAddress}</p>
        <RenderAfterNavermapsLoaded ncpClientId={"2ebca41zbe"}>
          <NaverMap
            mapDivId={"maps-getting-started-uncontrolled"} // default: react-naver-map
            style={{
              width: "80%",
              height: "400px",
            }}
            defaultCenter={position}
            defaultZoom={17}
          >
            <Marker position={position} />
          </NaverMap>
        </RenderAfterNavermapsLoaded>
        <button>레스토랑 길찾기</button>
      </div>
    </div>
  );
};

export default RestaurantInfo;
