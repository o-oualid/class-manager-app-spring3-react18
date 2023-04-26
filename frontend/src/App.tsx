import { createBrowserRouter, RouterProvider } from "react-router-dom";

import './App.scss';
import Home from "./pages/Home";

function App() : JSX.Element {

  const router = createBrowserRouter([
      {
          path: "/",
          element: <h1>Hi there</h1>
      },
      {
          path: "/home",
          element: <Home />
      }
    ])

  return (
    <div className="App">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
