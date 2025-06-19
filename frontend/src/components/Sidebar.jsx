import { useEffect, useState } from "react";

export default function Sidebar({ items = [] }) {
  // 紀錄目前頁面正在瀏覽哪一張卡片，用於高亮 active link
  const [activeIndex, setActiveIndex] = useState(null);

  // ⭐️ 監聽 scroll，計算最靠近頂端的卡片
  useEffect(() => {
    if (!items.length) return; // 沒資料就跳過

    const onScroll = () => {
      const OFFSET = 120; // 提前 120px 觸發 active
      let current = null;

      items.forEach((_, idx) => {
        const el = document.getElementById(`news-item-${idx}`);
        if (!el) return;
        if (window.scrollY >= el.offsetTop - OFFSET) {
          current = idx;
        }
      });
      setActiveIndex(current);
    };

    window.addEventListener("scroll", onScroll, { passive: true });
    onScroll(); // 初始化跑一次

    return () => window.removeEventListener("scroll", onScroll);
  }, [items]);

  if (!items.length) return null;

  return (
    <nav id="sidebar-nav">
      <h5>📑 新聞清單</h5>
      <ul className="sidebar-list">
        {items.map(({ title }, idx) => (
          <li className="sidebar-item" key={idx}>
            <span className="item-index">{idx + 1}.</span>
            <a
              href={`#news-item-${idx}`}
              className={activeIndex === idx ? "active" : ""}
              title={title}
            >
              {title.length > 40 ? `${title.slice(0, 37)}…` : title}
            </a>
          </li>
        ))}
      </ul>
    </nav>
  );
}
