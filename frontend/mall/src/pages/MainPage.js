import { Link } from "react-router-dom";

const MainPage = () => {
  return (
    <div>
      <div className="flex">
        <div>
          <Link to={"/about"}>About</Link>
        </div>
      </div>
      <div className="text-3xl">Main Page</div>
    </div>
  );
};

export default MainPage;
