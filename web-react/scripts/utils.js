const fs = require('fs');
const path = require('path');

const moveFile = (file, dir2) => {
    //gets file name and adds it to dir2
    const f = path.basename(file);
    const dest = path.resolve(dir2, f);

    if (!fs.existsSync(file)) {
        return;
    }
    fs.rename(file, dest, (err) => {
        if (err) throw err;
        else console.log('Successfully moved to:', dir2);
    });
};

module.exports = moveFile;