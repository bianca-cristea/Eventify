import { useState, useEffect } from "react";

const MyComponent = () => {
  const [imageSrc, setImageSrc] = useState<string | null>(null);

  useEffect(() => {
    setImageSrc(`/event-image-2.jpg`);
  }, []);

  return (
    <div>
      {imageSrc && (
        <img
          src={imageSrc}
          alt="Random Event"
          className="w-full h-auto rounded-lg"
        />
      )}
    </div>
  );
};

export default MyComponent;
