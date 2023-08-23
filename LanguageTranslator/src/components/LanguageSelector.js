import React, { useState, useEffect } from 'react';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';

function LanguageSelector() {
  const [languages, setLanguages] = useState([]);
  const [sourceLanguage, setSourceLanguage] = useState('');
  const [targetLanguage, setTargetLanguage] = useState('');

  useEffect(() => {
    // Fetch the list of supported languages from the backend using an API call
    // Replace the placeholder with actual API call to get supported languages
    const supportedLanguages = [
      { code: 'en', name: 'English' },
      { code: 'es', name: 'Spanish' },
      // Add more supported languages
    ];
    setLanguages(supportedLanguages);
  }, []);

  const handleSourceLanguageChange = (event) => {
    setSourceLanguage(event.target.value);
  };

  const handleTargetLanguageChange = (event) => {
    setTargetLanguage(event.target.value);
  };

  return (
    <div>
      <FormControl>
        <Select
          value={sourceLanguage}
          onChange={handleSourceLanguageChange}
          displayEmpty
        >
          <MenuItem value="" disabled>
            Select Source Language
          </MenuItem>
          {languages.map((language) => (
            <MenuItem key={language.code} value={language.code}>
              {language.name}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <FormControl>
        <Select
          value={targetLanguage}
          onChange={handleTargetLanguageChange}
          displayEmpty
        >
          <MenuItem value="" disabled>
            Select Target Language
          </MenuItem>
          {languages.map((language) => (
            <MenuItem key={language.code} value={language.code}>
              {language.name}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </div>
  );
}

export default LanguageSelector;
