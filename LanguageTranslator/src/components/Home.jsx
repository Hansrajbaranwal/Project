import React from 'react'

export const Home = () => {
    return (
        <div>
            <video autoPlay loop muted className="background-video">

                <source src={`${process.env.PUBLIC_URL}/office.mp4`} type="video/mp4" />

                Your browser does not support the video tag.

            </video>
            <div className='centered-container'>
                Welcome to Language Translator
            </div>
        </div>
    )
}
