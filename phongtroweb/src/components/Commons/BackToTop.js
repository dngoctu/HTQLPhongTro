import React, { useState, useEffect } from 'react';
import Styles from './Styles';
import { Button } from 'react-bootstrap';

const BackToTop = () => {
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    const toggleVisibility = () => {
      if (window.scrollY > 300) {
        setIsVisible(true);
      } else {
        setIsVisible(false);
      }
    };

    window.addEventListener('scroll', toggleVisibility);

    return () => {
      window.removeEventListener('scroll', toggleVisibility);
    };
  }, []);

  const scrollToTop = () => {
    window.scrollTo({
      behavior: 'smooth',
      top: 0
    });
  };

  return (
    <div style={Styles.backToTop}>
      {isVisible && (
        <Button
          onClick={scrollToTop}
          variant="info"
        >
          &#8679; Back to Top
        </Button>
      )}
    </div>
  );
};

export default BackToTop;
