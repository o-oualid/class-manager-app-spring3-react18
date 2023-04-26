import React from "react";
import { Badge, Button } from "@mantine/core"
import "../assets/main-page-image.jpg";

import "./styles/home.scss";

const Home: React.FC = (): JSX.Element => {
    return (
        <div className="home-container">
            <h1 className="title">Welcome to the best class management tool 👋</h1>
            <div className="horizontal-scroll-tags" >
                <Badge size="xl" variant="gradient" gradient={{ from: 'indigo', to: 'cyan' }}>Organized</Badge>
                <Badge size="xl" variant="gradient" gradient={{ from: 'teal', to: 'lime', deg: 105 }}>share with others</Badge>
                <Badge size="xl" variant="gradient" gradient={{ from: 'teal', to: 'blue', deg: 60 }}>ask for information</Badge>
                <Badge size="xl" variant="gradient" gradient={{ from: 'orange', to: 'red' }}>your class your little community</Badge>
                <Badge size="xl" variant="gradient" gradient={{ from: '#ed6ea0', to: '#ec8c69', deg: 35 }}>make progress</Badge>
            </div>
            <section className="main-section">
                <div className="left-block">
                    <h1 className="headline">Managing an <span>Online</span> classroom couldn't be easier</h1>
                    <h3 className="sub-headline">add students to your class, send them lessons and assignments files, set deadlines and give grades</h3>
                    <div className="action-buttons">
                        <Button onClick={() => window.location.href = '/register'} size="xl" mt="xl" variant="gradient" gradient={{ from: 'indigo', to: 'cyan' }}>Start Now</Button>
                        <Button onClick={() => window.location.href = '/about'} size="md" ml="xl" variant="outline" compact>learn more</Button>
                    </div>
                </div>
                <div className="right-block">
                    <img src={require("../assets/main-page-image.jpg")} alt="happy students" />
                </div>
            </section>
        </div>
    );
}

export default Home;
