// import React from 'react'
// import axios from 'axios';
// import { useEffect, useState } from 'react';


// function Translate() {
//     const [options, setOptions] = useState([])

//     const [to, setTo] = useState("en");

//     const [from, setFrom] = useState("en");

//     const [input, setInput] = useState("");

//     const [output, setOutput] = useState("");

//     const translate = () => {

//         const params = new URLSearchParams();

//         params.append('q', input);

//         params.append('source', from);

//         params.append('target', to);

//         params.append('api_key', 'xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx');

//         axios.post('https://libretranslate.com/translate', params,

//             {

//                 // q:input,

//                 // source: from,

//                 // traget: to,

//                 // api_key: 'xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx'

//             },

//             {
//                 headers: {

//                     'accept': 'application/json',

//                     'Content-Type': 'application/x-www-form-urlencoded',

//                 },

//             }).then(res => {

//                 console.log(res.data)

//                 setOutput(res.data.translatedText)

//             })

//     };

//     useEffect(() => {

//         axios.get('https://libretranslate.com/languages',

//             { headers: { 'accept': 'application/json' } }).then(res => {

//                 console.log(res.data);

//                 setOptions(res.data);

//             })

//     }, [])
//     return (
//         <div className="App">

//             <header><h1>Language Translator</h1></header>

//             <div>

//                 From ({from}):

//                 <select onChange={e => setFrom(e.target.value)}>

//                     {options.map(opt => <option value={opt.code}>{opt.name}</option>)}

//                 </select>

//                 To ({to}):

//                 <select onChange={e => setTo(e.target.value)}>

//                     {options.map(opt => <option value={opt.code}>{opt.name}</option>)}

//                 </select>

//             </div>

//             <div>

//                 <textarea cols="50" rows="5" onInput={(e) => setInput(e.target.value)}

//                 ></textarea>

//             </div>

//             <div>

//                 <textarea cols="50" rows="5" value={output}></textarea>

//             </div>

//             <div>

//                 <button onClick={e => translate()}>Translate</button>

//             </div>

//         </div>

//     );

// }

// export default Translate




import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Button } from '@mui/material';

function Translate() {
    const [options, setOptions] = useState([]);
    const [to, setTo] = useState("en");
    const [from, setFrom] = useState("en");
    const [input, setInput] = useState("");
    const [output, setOutput] = useState("");

    const translate = () => {
        const params = new URLSearchParams();
        params.append('q', input);
        params.append('source', from);
        params.append('target', to);
        // params.append('api_key',"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx" ); // Replace with a valid API key


        // body: JSON.stringify({
        //     q: "hello",
        //     source: "auto",
        //     target: "",
        //     format: "text",
        //     api_key: ""
        // }),

        axios.post('https://translate.argosopentech.com/translate', params.toString(), {
            //params.toString() is used to convert the URLSearchParams object into a URL-encoded string
            headers: {
                'accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded',
            },
        }).then(res => {
            console.log(res.data)
            setOutput(res.data.translatedText)
        }).catch(error => {
            console.error("Translation error:", error);
            setOutput("Translation error occurred.");
        });
    };

    useEffect(() => {
        axios.get('https://libretranslate.com/languages', {
            headers: {
                'accept': 'application/json',
            }
        }).then(res => {
            console.log(res.data);
            setOptions(res.data);
        }).catch(error => {
            console.error("Language options error:", error);
        });
    }, []);

    return (
        <div className="App">
            <video autoPlay loop muted className="background-video">

                <source src={`${process.env.PUBLIC_URL}/office.mp4`} type="video/mp4" />

                Your browser does not support the video tag.

            </video>
            <div className='centered-container'>
                <div className='content'>
                    <header><h4>Language Translator</h4></header>
                    <div>
                        From ({from}):
                        <select onChange={e => setFrom(e.target.value)}>
                            {options.map(opt => (
                                <option key={opt.code} value={opt.code}>{opt.name}</option>
                            ))}
                        </select>
                        To ({to}):
                        <select onChange={e => setTo(e.target.value)}>
                            {options.map(opt => (
                                <option key={opt.code} value={opt.code}>{opt.name}</option>
                            ))}
                        </select>
                    </div>
                    <div>
                        <textarea style={{fontSize:"18px"}} cols="50" rows="5" onInput={(e) => setInput(e.target.value)}></textarea>
                    </div>
                    <div>
                        <textarea style={{fontSize:"18px"}} cols="50" rows="5" value={output}></textarea>
                    </div>
                    <div>
                        <Button variant="contained" onClick={e => translate()}>Translate</Button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Translate;
