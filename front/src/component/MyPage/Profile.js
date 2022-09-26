import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import basicimage from "../../assets/images/user.png";
import styles from "./Profile.module.css";
import UploadProfilePic from "./EditUserInfo/UploadProfilePic";
import AuthContext from "../../store/auth-context";
import Button from "../UI/Button";
import EditUserInfo from "./EditUserInfo/EditUserInfo";
import Logobar from "../Layout/Logobar";
import FollowPage from "./Follow/FollowPage";

const Profile = (props) => {
  const ctx = useContext(AuthContext);
  const navigate = useNavigate();
  const [profileimg, setProfileimg] = useState(
    <img alt="img" src={basicimage} width="130px" />
  );
  const [editProfilePic, setEditProfilePic] = useState(false);
  const [editProfileInfo, setEditProfileInfo] = useState(false);
  const [showFollow, setShowFollow] = useState(false);

  const profilePicUplaod = () => {
    setEditProfilePic(true);
  };

  const editUserInfo = () => {
    setEditProfileInfo(true);
  };

  const showFollowList = () => {
    setShowFollow(true);
  };

  const logout = () => {
    localStorage.removeItem("token");
    ctx.onLogout();
    navigate("/");
  };

  const stateManage = () => {
    props.updateHandler();
  };

  return (
    <div>
      <Logobar />
      <div className={styles.pageTop}>
        <span className={styles.pageTitle}>마이 페이지</span>
        <button className={styles.logoutButton} onClick={logout}>
          로그아웃
        </button>
      </div>
      <div className={styles.profileImage}>
        {profileimg}
        <div className={styles.userInfo}>
          <div className={styles.nickname}>{props.user.nickname}</div>(
          {props.user.email})
          <div className={styles.followInfo} onClick={showFollowList}>
            팔로워 {props.user.followerList.length} &nbsp; &nbsp; 팔로잉{" "}
            {props.user.followingList.length}
          </div>
        </div>
      </div>
      <div className={styles.mypageButtons}>
        <Button onClick={profilePicUplaod} photoUpdate={() => setProfileimg()}>
          사진 등록
        </Button>
        &nbsp;&nbsp;
        <Button onClick={editUserInfo}>회원정보 수정</Button>
      </div>
      <div className={styles.grayline}>&nbsp;</div>
      {showFollow ? (
        <FollowPage
          data={props.user}
          closeModal={() => {
            setShowFollow(false);
          }}
        />
      ) : (
        ""
      )}
      {editProfilePic ? (
        <UploadProfilePic
          id={props.user.userIndex}
          email={props.user.email}
          password={props.user.password}
          nickname={props.user.nickname}
          closeModal={() => {
            setEditProfilePic(false);
          }}
          stateManage={() => {
            stateManage();
          }}
        />
      ) : (
        ""
      )}
      {editProfileInfo ? (
        <EditUserInfo
          email={props.user.email}
          password={props.user.password}
          nickname={props.user.nickname}
          closeModal={() => {
            setEditProfileInfo(false);
          }}
          stateManage={() => {
            stateManage();
          }}
        />
      ) : (
        ""
      )}
    </div>
  );
};

export default React.memo(Profile);
