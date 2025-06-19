import { useEffect, useState } from "react";
import { fetchCartNews } from "./api/cart";
import NewsCard from "./components/NewsCard"; // 下一步會實作

export default function App() {
  const [newsList, setNewsList] = useState([]);
  const [loading, setLoading] = useState(true);

  //  ⭐️ 新增：componentDidMount
  useEffect(() => {
    /** 初始化 => 從 localStorage 撈 IDs 再叫後端 */
    const ids = JSON.parse(localStorage.getItem("newsCart") || "[]")
      .filter((id) => Number.isInteger(id) && id > 0);

    if (ids.length === 0) {
      setLoading(false);
      return;
    }
    fetchCartNews(ids)
      .then(setNewsList)
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p className="text-center mt-5">載入中…</p>;
  if (newsList.length === 0)
    return <p className="text-center mt-5">📭 目前沒有收藏新聞</p>;

  return (
    <main className="feed-container">
      <h1 className="text-center mb-4">📰 我的新聞牆</h1>
      {newsList.map((n) => (
        <NewsCard key={n.id} news={n} />
      ))}
    </main>
  );
}
