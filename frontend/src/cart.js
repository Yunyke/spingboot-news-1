import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8008/api", //  ⭐️ 新增：你的 Spring 埠號
});

/** 依照 ID 陣列取得新聞清單 */
export const fetchCartNews = (ids) =>
  api.get("/cart", { params: { ids } }).then((r) => r.data);

/** 把單一新聞加進購物車（回傳完整 News 物件） */
export const addNewsToCart = (newsId) =>
   api.post("/cart", { id: newsId })   
    .then((r) => r.data);

    export const removeNewsFromCart = (newsId) =>   // ★ 新增刪除 API
  api.delete(`/cart/${newsId}`).then(() => newsId);