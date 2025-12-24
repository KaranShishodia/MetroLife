const API_URL = "http://localhost:8080";

window.onload = async function() {
    try {
        const response = await fetch(`${API_URL}/stations`);
        const text = await response.text();
        const stations = text.replace(/[\[\]]/g, '').split(',').map(s => s.trim());

        const startSelect = document.getElementById('start-station');
        const endSelect = document.getElementById('end-station');

        stations.sort().forEach(station => {
            if(station) {
                startSelect.add(new Option(station, station));
                endSelect.add(new Option(station, station));
            }
        });
    } catch (error) {
        console.error("Backend offline:", error);
    }
};

async function findPath() {
    const start = document.getElementById('start-station').value;
    const end = document.getElementById('end-station').value;
    const resultBox = document.getElementById('result-container');

    if (start.includes("Choose") || end.includes("Choose")) {
        alert("Please select valid stations.");
        return;
    }

    try {
        const response = await fetch(`${API_URL}/find-path?start=${start}&end=${end}`);
        const text = await response.text();
        
        // Clean up the format: [A, B, C] -> A -> B -> C
        const formattedPath = text.replace(/[\[\]]/g, '')
                                .split(',')
                                .map(s => s.trim())
                                .join(' <i class="fa-solid fa-arrow-right" style="font-size:0.7em; color:#64748b; margin:0 5px;"></i> ');

        document.getElementById('path-display').innerHTML = formattedPath;
        resultBox.classList.remove('hidden');
    } catch (error) {
        alert("Error calculating path.");
    }
}