import { useState } from "react";

export default function NewsCard({ news, onRemove, idx }) {
  const { id, title, description, imageUrl, publishedAt, content } = news;
  const [showFullContent, setShowFullContent] = useState(false);

  return (
    <div className="news-feed-card">
      {imageUrl && (
        <img src={imageUrl} alt={title} className="news-feed-img" />
      )}

      <div className="news-feed-title">{title}</div>
      <div className="news-feed-desc">{description}</div>
      <div className="news-feed-time">
        {new Date(publishedAt).toLocaleString("zh-TW")}
      </div>

      {content && showFullContent && (
        <div className="news-feed-content">{content}</div>
      )}

      <div className="news-feed-actions">
        {content && (
          <button
            className="btn btn-sm btn-outline-primary"
            onClick={() => setShowFullContent((prev) => !prev)}
          >
            {showFullContent ? "收起全文" : "閱讀全文"}
          </button>
        )}
        <button
          className="btn btn-sm btn-outline-danger"
          onClick={() => onRemove(id)}
        >
          移除
        </button>
      </div>
    </div>
  );
}
