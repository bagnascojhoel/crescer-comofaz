import './styles.css';
import React, { useState } from 'react';
import { Star } from './star/star.component';

export function Rating({
  onClick,
  value,

  largeIcon,
  flexible,

  className,
}) {
  const [hover, setHover] = useState(null);
  const handleClick = (v) => {
    if (onClick) onClick(v);
  };

  const handleMouseEnter = (v) => onClick && setHover(v);
  const handleMouseLeave = () => setHover(null);

  const props = {
    rate: hover ? hover : value,
    large: largeIcon,
    flexible: flexible,
    onClick: handleClick,
    onMouseEnter: handleMouseEnter,
  };

  return (
    <div className={`rating ${className}`} onMouseLeave={handleMouseLeave}>
      <Star value={0} {...props} ghost />
      <Star value={1} {...props} />
      <Star value={2} {...props} />
      <Star value={3} {...props} />
      <Star value={4} {...props} />
      <Star value={5} {...props} />
    </div>
  );
}

Rating.defaultProps = {
  max: 5,
};
