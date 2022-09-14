import React, { useState } from 'react';
import ReviewDetail from '../Review/ReviewDetail';
import styles from './ReviewListItem.module.css';

const ReviewListItem = (props) => {
  const [modalView, setModalView] = useState(false);

  const modalViewHandler = () => {
    setModalView(!modalView);
  };

  return (
    <li key={props.review.id} className={styles.container}>
      <div className={styles.reviewInfo}>
        {props.review.user.profileImg === null ? (
          <img alt='avatar' src={require('../../assets/images/user.png')} />
        ) : (
          <img alt='avatar' src={props.review.user.profileImg} />
        )}
        <div>
          <h6>{props.review.user.name}</h6>
          <p>{props.review.date}</p>
        </div>
      </div>
      <div className={styles.reviewContent} onClick={modalViewHandler}>
        <ul className={styles.reviewImage}>
          {props.review.img === null && ''}
          {props.review.img?.length > 3 && (
            <>
              <li>
                <img alt='리뷰 이미지' src={props.review.img[0].url} />
              </li>
              <li>
                <img alt='리뷰 이미지' src={props.review.img[1].url} />
              </li>
              <li>
                <img alt='리뷰 이미지' src={props.review.img[2].url} />
              </li>
              <li className={styles.reviewImageOver}>
                <div className={styles.imgBlack}>+{props.review.img?.length - 3}</div>
                <img alt='리뷰 이미지' src={props.review.img[3].url} />
              </li>
            </>
          )}
          {props.review.img?.length <= 3 &&
            props.review.img.map((item) => {
              return (
                <li>
                  <img alt='리뷰 이미지' src={item.url} />
                </li>
              );
            })}
        </ul>
        <h4>"{props.review.comment}"</h4>
        <p>{props.review.review}</p>
        {/* <button>더보기</button> */}
        {modalView ? (
          <ReviewDetail
            review={props.review}
            closeModal={() => {
              setModalView(false);
            }}
          />
        ) : (
          ''
        )}
      </div>
    </li>
  );
};

export default ReviewListItem;
