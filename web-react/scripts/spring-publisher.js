// This file is wrapper of parcel bundle for setting up environment that is required
// for creation spring jar that contains html(templating) file with imported script file. Both files
// are output of parcel in given location (outDir option). After bundling we need to take
// output html file and copy it to template folder in spring resources location.
//
// INPUTS: Script accepts two arguments in this order:
// 1) Path where files are copied 2) Environment (production/development)

const moveFile = require('./utils');
const bundler = require('parcel-bundler');
const path = require('path');

const staticFolderLocation = process.argv[2];
console.log('Static folder location:', staticFolderLocation);
const env = process.argv[3];
process.env.NODE_ENV = env;

// entry files
const entryFiles = path.join(__dirname, '../src/react.html');
// absolute (full) path to folder containing static resources in spring project
const resourceAbsolutePath = path.join(__dirname, '..', staticFolderLocation);
// name of output html file
const outFileName = 'react.mustache';

// bundler options
const options = {
    outDir: staticFolderLocation, // The out directory to put the build files in, defaults to dist
    outFile: outFileName
};

const bundlerInstance = new bundler(entryFiles, options);

// On bundle we copy entry index file that has paths to generated scripts
// to template directory
bundlerInstance.on('bundled', (bundle) => {
    moveFile(path.join(resourceAbsolutePath, outFileName), path.join(resourceAbsolutePath, '../templates'))
});

// Call this to start bundling
bundlerInstance.bundle();